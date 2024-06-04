package com.mini.asaas.payment

import com.mini.asaas.utils.DateFormatUtils
import com.mini.asaas.utils.StringUtils
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
        this.originalValue = StringUtils.formatFromCommaToDot(params.originalValue) as BigDecimal
        this.interestValue = StringUtils.formatFromCommaToDot(params.interestValue) as BigDecimal
        this.discountValue = StringUtils.formatFromCommaToDot(params.discountValue) as BigDecimal
        this.netValue = StringUtils.formatFromCommaToDot(params.netValue) as BigDecimal
        this.description = params.description
        this.billingType = BillingType.parseFromString(params.billingType)
        this.status = PaymentStatus.PENDING
        this.dueDate = DateFormatUtils.parseDateFromString(params.dueDate)
    }
}
