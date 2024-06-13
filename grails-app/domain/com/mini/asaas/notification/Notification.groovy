package com.mini.asaas.notification

import com.mini.asaas.base.BaseEntity
import com.mini.asaas.customer.Customer

class Notification extends BaseEntity {

    String title

    String message

    NotificationEvent event

    String link

    Boolean isRead

    Customer customer

    static constraints = {
        title blank: false
        message blank: false
        link nullable: true
    }
}
