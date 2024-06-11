package com.mini.asaas.payment

import com.mini.asaas.base.BaseEntity
import com.mini.asaas.customer.Customer
import com.mini.asaas.payer.Payer

class Payment extends BaseEntity {

    String publicId

    Customer customer

    Payer payer

    BigDecimal value

    String description

    BillingType billingType

    PaymentStatus status

    Date dueDate

    Date paymentDate

    static constraints = {
        publicId nullable: true
        value scale: 2
        description nullable: true, maxSize: 255
        paymentDate nullable: true
    }
}
