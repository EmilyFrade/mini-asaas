package com.mini.asaas.payment

import com.mini.asaas.base.BaseValidator
import com.mini.asaas.customer.Customer
import com.mini.asaas.payer.Payer

class PaymentValidator extends BaseValidator {

    public PaymentValidator validateAll(PaymentAdapter adapter) {
        this.validateCustomer(adapter.customerId)
        this.validatePayer(adapter.payerId)
        this.validateValues(adapter)
        this.validateDueDate(adapter.dueDate)
        this.validateEnum(adapter)

        return this
    }

    public PaymentValidator validateCustomer(Long id) {
        Customer customer = Customer.get(id)
        if (!customer || customer.deleted) {
            validationResult.addError("invalid.customer")
        }

        return this
    }

    public PaymentValidator validatePayer(long id) {
        Payer payer = Payer.get(id)
        if (!payer || payer.deleted) {
            validationResult.addError("invalid.payer")
        }

        return this
    }

    public PaymentValidator validateValues(PaymentAdapter adapter) {
        if (adapter.netValue <= 0 || adapter.originalValue <= 0) {
            validationResult.addError("invalid.value")
        }
        if (adapter.interestValue < 0 || adapter.discountValue < 0) {
            validationResult.addError("invalid.value")
        }

        return this
    }

    public PaymentValidator validateDueDate(Date dueDate) {
        if (dueDate < new Date()) {
            validationResult.addError("past.dueDate")
        }

        return this
    }

    public PaymentValidator validateEnum(PaymentAdapter adapter) {
        if (!(adapter.billingType in BillingType.values())) {
            validationResult.addError("invalid.billingType")
        }
        if (!(adapter.status in PaymentStatus.values())) {
            validationResult.addError("invalid.paymentStatus")
        }

        return this
    }
}

