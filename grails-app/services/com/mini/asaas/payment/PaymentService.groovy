package com.mini.asaas.payment

import com.mini.asaas.customer.Customer
import com.mini.asaas.exceptions.BusinessException
import com.mini.asaas.payer.Payer
import com.mini.asaas.utils.DomainErrorUtils
import com.mini.asaas.validation.BusinessValidation
import grails.gorm.transactions.Transactional

@Transactional
class PaymentService {

    BusinessValidation validationResult

    public Payment save(PaymentAdapter adapter) {
        Payment payment = new Payment()

        payment = validate(adapter, payment)

        if (payment.hasErrors()) throw new BusinessException(DomainErrorUtils.getFirstValidationMessage(payment))

        payment = buildPayment(adapter, payment)

        return payment.save(failOnError: true)
    }

    public Payment update(PaymentAdapter adapter, Long id) {
        Payment payment = PaymentRepository.query([includeDeleted: true, id: id]).get()

        if (!payment) throw new RuntimeException("Pagador não encontrado")

        payment = validate(adapter, payment)

        if (payment.hasErrors()) throw new BusinessException(DomainErrorUtils.getFirstValidationMessage(payment))

        payment = buildPayment(adapter, payment)

        return payment.save(failOnError: true)
    }

    public Payment show(Long id) {
        Payment payment = PaymentRepository.query([includeDeleted: true, id: id]).get()
        if (!payment) throw new RuntimeException("Cobrança não encontrada")

        return payment
    }

    public void delete(Long id) {
        Payment payment = PaymentRepository.get(id)
        if (!payment) throw new RuntimeException("Cobrança não encontrada")
        payment.deleted = true

        payment.save(failOnError: true)
    }

    public void restore(Long id) {
        Payment payment = PaymentRepository.query([deletedOnly: true, id: id]).get()
        if (!payment) throw new RuntimeException("Cobrança não encontrada")
        payment.deleted = false

        payment.save(failOnError: true)
    }

    public static void setPaymentAsOverdue(Payment payment) {
        payment.status = PaymentStatus.OVERDUE
        payment.save(flush: true)
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
        payment.customer = Customer.get(adapter.customerId)
        payment.payer = Payer.get(adapter.payerId)
        payment.value = adapter.value
        payment.description = adapter.description
        payment.billingType = adapter.billingType
        payment.status = adapter.status
        payment.dueDate = adapter.dueDate

        return payment
    }
}