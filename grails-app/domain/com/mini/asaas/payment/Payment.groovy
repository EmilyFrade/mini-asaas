package com.mini.asaas.payment

import com.mini.asaas.base.BaseEntity
import com.mini.asaas.customer.Customer
import com.mini.asaas.payer.Payer

class Payment extends BaseEntity {

    Customer customer

    Payer payer

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

    Date paymentDate

    static constraints = {
        customer blank: false
        payer blank: false
        netValue blank: false, scale: 2
        originalValue blank: false, scale: 2
        interestValue nullable: true, scale: 2
        discountValue nullable: true, scale: 2
        description nullable: true, maxSize: 255
        billingType blank: false
        status blank: false
        dueDate blank: false
        paymentDate nullable: true
    }
}
