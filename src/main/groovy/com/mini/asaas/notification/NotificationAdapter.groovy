package com.mini.asaas.notification

import com.mini.asaas.user.User
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class NotificationAdapter {

    String title

    String message

    NotificationEvent event

    NotificationLink link

    User user

    public NotificationAdapter(Map map) {
        this.title = map.title
        this.message = map.message
        this.event = map.event as NotificationEvent
        this.link = map.link as NotificationLink
        this.user = map.user as User
    }
}
