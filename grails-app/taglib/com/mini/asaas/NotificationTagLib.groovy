package com.mini.asaas

import com.mini.asaas.atlas.AtlasIcon
import com.mini.asaas.atlas.AtlasTheme
import com.mini.asaas.notification.Notification
import com.mini.asaas.notification.NotificationEvent
import com.mini.asaas.notification.NotificationService

class NotificationTagLib {
    static namespace = "notificationTagLib"

    NotificationService notificationService

    def dropdown = { attrs, body ->
        List<Notification> allNotifications = notificationService.list()
        Integer unreadCount = allNotifications.count { !it.isRead }.toInteger()

        out << g.render(
            template: "/notification/notifications-dropdown",
            model: [notifications: allNotifications, count: unreadCount]
        )
    }

    def card = { attrs, body ->
        Notification notification = attrs.notification as Notification

        AtlasIcon icon = AtlasIcon.BELL
        AtlasIcon overlayIcon = null
        AtlasTheme overlayTheme = AtlasTheme.WARNING

        switch (notification.event) {
            case NotificationEvent.PAYMENT_CREATED: {
                icon = AtlasIcon.FILE_DOLLAR
                overlayTheme = AtlasTheme.SUCCESS
                break
            }
            case NotificationEvent.PAYMENT_OVERDUE: {
                icon = AtlasIcon.FILE_DOLLAR
                overlayIcon = AtlasIcon.ALERT_CIRCLE
                overlayTheme = AtlasTheme.DANGER
                break
            }
            case NotificationEvent.PAYMENT_RECEIVED: {
                icon = AtlasIcon.HAND_HOLDING_MONEY
                overlayIcon = AtlasIcon.CELEBRATE
                overlayTheme = AtlasTheme.SUCCESS
                break
            }
            case NotificationEvent.PAYMENT_DELETED: {
                icon = AtlasIcon.TRASH
                overlayIcon = AtlasIcon.ALERT_CIRCLE
                overlayTheme = AtlasTheme.DANGER
                break
            }
        }

        if (notification) {
            out << g.render(
                template: "/notification/notification-card",
                model: [
                    notification: notification,
                    icon        : icon,
                    overlayIcon : overlayIcon,
                    overlayTheme: overlayTheme
                ]
            )
        }
    }
}
