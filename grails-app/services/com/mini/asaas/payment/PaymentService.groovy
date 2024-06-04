package com.mini.asaas.payment

import com.mini.asaas.customer.Customer
import com.mini.asaas.exceptions.BusinessException
import com.mini.asaas.payer.Payer
import com.mini.asaas.utils.DomainErrorUtils
import com.mini.asaas.validation.BusinessValidation

class PaymentService {

    BusinessValidation validationResult

    public Payment save(PaymentAdapter adapter) {
        Payment payment = new Payment()

        payment = validate(adapter, payment)

        if (payment.hasErrors()) throw new BusinessException(DomainErrorUtils.getFirstValidationMessage(payment))

        payment = buildPayment(adapter, payment)

        return payment.save(failOnError: true)
    }

    private Payment validate(PaymentAdapter adapter, Payment payment) {
        PaymentValidator validator = new PaymentValidator()
        validator.validateAll(adapter)

        validationResult = validator.validationResult

        if (!validationResult.isValid()) {
            DomainErrorUtils.addBusinessRuleErrors(payment, validationResult.errors)
        }

        return payment
    }

    private Payment buildPayment(PaymentAdapter adapter, Payment payment) {
        payment.customer = Customer.get(adapter.customerId)
        payment.payer = Payer.get(adapter.payerId)
        payment.netValue = adapter.netValue
        payment.originalValue = adapter.originalValue
        payment.interestValue = adapter.interestValue
        payment.discountValue = adapter.discountValue
        payment.description = adapter.description
        payment.billingType = adapter.billingType
        payment.status = adapter.status
        payment.dueDate = adapter.dueDate

        return payment
    }
}