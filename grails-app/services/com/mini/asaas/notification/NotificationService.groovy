package com.mini.asaas.notification

import com.mini.asaas.user.User
import com.mini.asaas.user.UserRepository
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService

@Transactional
class NotificationService {

    SpringSecurityService springSecurityService

    public Notification save(NotificationAdapter adapter) {
        Notification notification = new Notification()
        notification.title = adapter.title
        notification.message = adapter.message
        notification.priority = adapter.priority
        notification.event = adapter.event
        notification.link = adapter.link
        notification.customer = adapter.user.customer
        notification.user = adapter.user
        notification.save(failOnError: true)

        return notification
    }

    public Notification markAsRead(Long notificationId, Long userId) {
        User user = UserRepository.get(userId)
        if (!user) throw new RuntimeException("Usuário não encontrado")

        Long customerId = user.customerId
        Notification notification = NotificationRepository.query([customerId: customerId, id: notificationId]).get()
        if (!notification) throw new RuntimeException("Notificação não encontrada")

        notification.isRead = true
        notification.save(failOnError: true)

        return notification
    }

    public List<Notification> list() {
        Long customerId = (springSecurityService.loadCurrentUser() as User).customerId

        List<Notification> notificationList = NotificationRepository
            .query([customerId: customerId])
            .sort([[column: "priority", order: "desc"]])
            .list()

        return notificationList
    }
}
