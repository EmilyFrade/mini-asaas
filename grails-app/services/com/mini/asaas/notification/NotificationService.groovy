package com.mini.asaas.notification

import com.mini.asaas.user.User
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService

@Transactional
class NotificationService {

    SpringSecurityService springSecurityService

    public Notification save(NotificationAdapter adapter) {
        Notification notification = new Notification()
        notification.title = adapter.title
        notification.message = adapter.message
        notification.event = adapter.event
        notification.link = adapter.link
        notification.customer = adapter.customer
        notification.isRead = false
        notification.save(failOnError: true)

        return notification
    }

    public List<Notification> list() {
        Long customerId = (springSecurityService.loadCurrentUser() as User).customerId

        return NotificationRepository.query([customerId: customerId]).list()
    }
}
