package com.mini.asaas.payment

import com.mini.asaas.base.BaseValidator
import com.mini.asaas.payer.Payer

class PaymentValidator extends BaseValidator {

    public PaymentValidator validateAll(PaymentAdapter adapter) {
        this.validatePayer(adapter.payerId)
        this.validateValue(adapter.value)
        this.validateDueDate(adapter.dueDate)
        this.validateBillingType(adapter.billingType)

        return this
    }

    public PaymentValidator validatePayer(long id) {
        Payer payer = Payer.get(id)
        if (!payer || payer.deleted) {
            validationResult.addError("invalid.payer")
        }

        return this
    }

    public PaymentValidator validateValue(BigDecimal value) {
        if (value <= 0) {
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

    public PaymentValidator validateBillingType(BillingType billingType) {
        if (!(billingType in BillingType.values())) {
            validationResult.addError("invalid.billingType")
        }

        return this
    }
}

