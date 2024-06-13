package com.mini.asaas.notification

import com.mini.asaas.customer.Customer
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class NotificationAdapter {

    String title

    String message

    NotificationEvent event

    String link

    Customer customer

    public NotificationAdapter(Map map) {
        this.title = map.title
        this.message = map.message
        this.event = map.event as NotificationEvent
        this.link = map.link as NotificationLink
        this.customer = map.customer as Customer
    }
}
