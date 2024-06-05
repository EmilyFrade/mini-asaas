package com.mini.asaas.payment

import com.mini.asaas.utils.DateFormatUtils
import com.mini.asaas.utils.PaymentUtils
import com.mini.asaas.utils.StringUtils
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
        this.originalValue = StringUtils.formatFromCommaToDot(params.originalValue) as BigDecimal
        this.interestPercentual = params.interestPercentual as BigDecimal
        this.interestValue = PaymentUtils.calculateInterestValue(originalValue, interestPercentual)
        this.discountPercentual = params.discountPercentual as BigDecimal
        this.discountValue = PaymentUtils.calculateDiscountValue(originalValue, discountPercentual)
        this.netValue = PaymentUtils.calculateNetValue(originalValue, discountValue)
        this.description = params.description
        this.billingType = BillingType.parseFromString(params.billingType)
        this.status = PaymentStatus.PENDING
        this.dueDate = DateFormatUtils.parseDateFromString(params.dueDate)
    }
}
