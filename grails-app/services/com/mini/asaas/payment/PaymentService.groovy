package com.mini.asaas.payment

import com.mini.asaas.exceptions.BusinessException
import com.mini.asaas.payer.Payer
import com.mini.asaas.user.User
import com.mini.asaas.utils.DateFormatUtils
import com.mini.asaas.utils.DomainErrorUtils
import com.mini.asaas.validation.BusinessValidation
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService

@Transactional
class PaymentService {

    BusinessValidation validationResult

    PaymentEventNotificationService paymentEventNotificationService

    SpringSecurityService springSecurityService

    public Payment save(PaymentAdapter adapter) {
        Payment payment = new Payment()

        User user = springSecurityService.loadCurrentUser() as User
        payment.customer = user.customer
        if (!payment.customer) throw new BusinessException("Cliente não encontrado")

        payment = validate(adapter, payment)

        if (payment.hasErrors()) throw new BusinessException(DomainErrorUtils.getFirstValidationMessage(payment))

        payment = buildPayment(adapter, payment)

        payment = payment.save(failOnError: true)

        paymentEventNotificationService.onSave(payment, user)

        return payment
    }

    public Payment update(PaymentAdapter adapter, Long id) {
        Long customerId = (springSecurityService.loadCurrentUser() as User).customerId
        Payment payment = PaymentRepository.query([includeDeleted: true, id: id, customerId: customerId]).get()

        if (!payment) throw new RuntimeException("Cobrança não encontrada")

        payment = validate(adapter, payment)

        if (payment.hasErrors()) throw new BusinessException(DomainErrorUtils.getFirstValidationMessage(payment))

        payment = buildPayment(adapter, payment)

        return payment.save(failOnError: true)
    }

    public Payment show(Long id) {
        Long customerId = (springSecurityService.loadCurrentUser() as User).customerId
        Payment payment = PaymentRepository.query([includeDeleted: true, id: id, customerId: customerId]).get()
        if (!payment) throw new RuntimeException("Cobrança não encontrada")

        return payment
    }

    public void delete(Long id) {
        User user = springSecurityService.loadCurrentUser() as User
        Long customerId = user.customerId
        Payment payment = PaymentRepository.query([id: id, customerId: customerId]).get()
        if (!payment) throw new RuntimeException("Cobrança não encontrada")
        if (!payment.status.canBeDeleted()) throw new BusinessException("Cobrança não pode ser deletada")

        payment.deleted = true
        payment.status = PaymentStatus.CANCELED

        payment.save(failOnError: true)

        paymentEventNotificationService.onDelete(payment, user)
    }

    public void restore(Long id) {
        Long customerId = (springSecurityService.loadCurrentUser() as User).customerId
        Payment payment = PaymentRepository.query([deletedOnly: true, id: id, customerId: customerId]).get()
        if (!payment) throw new RuntimeException("Cobrança não encontrada")
        if (payment.dueDate < new Date()) throw new BusinessException("A data de vencimento não pode ser uma data passada")

        payment.deleted = false
        payment.status = PaymentStatus.PENDING

        payment.save(failOnError: true)
    }

    public void receive(Long id) {
        Payment payment = PaymentRepository.get(id)
        if (!payment) throw new RuntimeException("Cobrança não encontrada")
        if (!payment.status.canBeReceived()) throw new BusinessException("Apenas cobranças com status 'Aguardando pagamento' podem ser recebidas")

        payment.status = PaymentStatus.RECEIVED
        payment.paymentDate = new Date()
        generateReceiptId(payment)

        payment.save(failOnError: true)

        paymentEventNotificationService.onReceive(payment, springSecurityService.loadCurrentUser() as User)
    }

    public List<Payment> list(String status) {
        Long customerId = (springSecurityService.loadCurrentUser() as User).customerId
        Map queryParams = [customerId: customerId, includeDeleted: true, "status[in]": status.split(",")]
        return PaymentRepository.query(queryParams).list()
    }

    public void setPaymentsAsOverdue() {
        Map params = [
                "dueDate[lt]": DateFormatUtils.getDateWithoutTime(),
                status       : PaymentStatus.PENDING
        ]

        List<Long> paymentIdList = PaymentRepository.query(params).column("id").list()

        for (Long id : paymentIdList) {
            Payment.withNewTransaction { status ->
                try {
                    Payment payment = PaymentRepository.get(id)
                    payment.status = PaymentStatus.OVERDUE
                    payment.save(failOnError: true)
                    paymentEventNotificationService.onOverdue(payment)
                } catch (Exception exception) {
                    status.setRollbackOnly()
                }
            }
        }
    }

    private Payment validate(PaymentAdapter adapter, Payment validatedPayment) {
        PaymentValidator validator = new PaymentValidator()

        validator.validateAll(adapter)
        validationResult = validator.validationResult

        if (!validationResult.isValid()) {
            DomainErrorUtils.addBusinessRuleErrors(validatedPayment, validationResult.errors)
        }

        return validatedPayment
    }

    private Payment buildPayment(PaymentAdapter adapter, Payment payment) {
        payment.payer = Payer.get(adapter.payerId)
        payment.value = adapter.value
        payment.description = adapter.description
        payment.billingType = adapter.billingType
        payment.status = adapter.status
        payment.dueDate = adapter.dueDate

        return payment
    }

    private void generateReceiptId(Payment payment) {
        if (payment.status != PaymentStatus.RECEIVED || payment.receiptId != null) throw new RuntimeException("Cobrança inválida")

        payment.receiptId = UUID.randomUUID().toString()
    }
}