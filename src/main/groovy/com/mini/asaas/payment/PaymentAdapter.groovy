package com.mini.asaas.payment

import com.mini.asaas.utils.BigDecimalUtils
import com.mini.asaas.utils.DateFormatUtils
import com.mini.asaas.utils.Utils

class PaymentAdapter {

    Long customerId

    Long payerId

    BigDecimal netValue

    BigDecimal originalValue

    BigDecimal interestPercentual

    BigDecimal interestValue

    BigDecimal discountPercentual

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
        this.originalValue = BigDecimalUtils.fromFormattedString(params.originalValue)
        this.interestPercentual = BigDecimalUtils.fromFormattedString(params.interestPercentual)
        this.interestValue = BigDecimalUtils.calculateInterestValue(originalValue, interestPercentual)
        this.discountPercentual = BigDecimalUtils.fromFormattedString(params.discountPercentual)
        this.discountValue = BigDecimalUtils.calculateDiscountValue(originalValue, discountPercentual)
        this.netValue = BigDecimalUtils.calculateNetValue(originalValue, discountValue)
        this.description = params.description
        this.billingType = BillingType.parseFromString(params.billingType)
        this.status = PaymentStatus.PENDING
        this.dueDate = DateFormatUtils.parseDateFromString(params.dueDate)
    }
}
