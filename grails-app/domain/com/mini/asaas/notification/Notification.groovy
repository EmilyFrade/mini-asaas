package com.mini.asaas.notification

import com.mini.asaas.base.BaseEntity
import com.mini.asaas.customer.Customer
import com.mini.asaas.user.User

class Notification extends BaseEntity {

    String title

    String message

    NotificationEvent event

    NotificationLink link

    Boolean isRead

    Customer customer

    static constraints = {
        title blank: false
        message blank: false
        link nullable: true, validator: { NotificationLink link, Notification notification ->
            if (link && !link.validate()) {
                return "default.invalid.validator.message"
            }
        }
    }

    static embedded = ["link"]
}
