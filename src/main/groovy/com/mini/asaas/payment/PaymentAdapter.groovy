package com.mini.asaas.payment


import com.mini.asaas.utils.DateFormatUtils
import com.mini.asaas.utils.Utils

class PaymentAdapter {

    Long customerId

    Long payerId

    BigDecimal netValue

    BigDecimal originalValue

    BigDecimal interestValue

    BigDecimal discountValue

    String description

    BillingType billingType

    PaymentStatus status

    Date dueDate

    public PaymentAdapter(Map originalParams) {
        Map<String, String> params = Utils.normalizeParams(originalParams)
        if (!params) return
        this.customerId = params.customerId as Long
        this.payerId = params.payerId as Long
        this.netValue = params.netValue as BigDecimal
        this.originalValue = params.originalValue as BigDecimal
        this.interestValue = params.interestValue as BigDecimal
        this.discountValue = params.discountValue as BigDecimal
        this.description = params.description
        this.billingType = BillingType.parseFromString(params.billingType)
        this.status = PaymentStatus.parseFromString(params.status)
        this.dueDate = DateFormatUtils.parseDateFromString(params.dueDate)
    }
}
