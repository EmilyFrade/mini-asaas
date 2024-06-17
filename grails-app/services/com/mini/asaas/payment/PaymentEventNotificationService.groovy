package com.mini.asaas.payment

import com.mini.asaas.email.EmailAdapter
import com.mini.asaas.email.EmailMessageService
import com.mini.asaas.notification.NotificationAdapter
import com.mini.asaas.notification.NotificationEvent
import com.mini.asaas.notification.NotificationService
import com.mini.asaas.user.User
import com.mini.asaas.utils.DateFormatUtils
import grails.gorm.transactions.Transactional
import grails.gsp.PageRenderer

@Transactional
class PaymentEventNotificationService {

    EmailMessageService emailMessageService

    NotificationService notificationService

    PageRenderer groovyPageRenderer

    public void onSave(Payment payment, User user) {
        String body = groovyPageRenderer.render(
            template: "/payment/templates/email/payment-created",
            model: [payment: payment, user: user, baseUrl: System.getenv("BASE_URL")]
        )

        emailMessageService.save(new EmailAdapter([
            to     : payment.payer.email,
            subject: "Nova Cobrança",
            body   : body,
            isHTML : true
        ]))

        notificationService.save(new NotificationAdapter([
                title     : "Nova cobrança criada",
                message   : "O usuário com email ${user.email} criou uma cobrança no dia ${DateFormatUtils.formatWithTime(payment.dateCreated)}",
                event     : NotificationEvent.PAYMENT_CREATED,
                link      : "http://localhost:8080/payment/show/${payment.id}",
                customer  : payment.customer
        ]))
    }

    public void onDelete(Payment payment, User user) {
        String body = groovyPageRenderer.render(
            template: "/payment/templates/email/payment-deleted",
            model: [payment: payment, user: user, baseUrl: System.getenv("BASE_URL")]
        )

        emailMessageService.save(new EmailAdapter([
            to     : payment.payer.email,
            subject: "Cobrança Cancelada",
            body   : body,
            isHTML : true
        ]))

        notificationService.save(new NotificationAdapter([
                title     : "Cobrança cancelada",
                message   : "O usuário com email ${user.email} cancelou uma cobrança no dia ${DateFormatUtils.formatWithTime(payment.dateCreated)}",
                event     : NotificationEvent.PAYMENT_DELETED,
                link      : "http://localhost:8080/payment/show/${payment.id}",
                customer  : payment.customer
        ]))
    }

    public void onReceive(Payment payment, User user) {
        String body = groovyPageRenderer.render(
            template: "/payment/templates/email/payment-received",
            model: [payment: payment, user: user, baseUrl: System.getenv("BASE_URL")]
        )

        emailMessageService.save(new EmailAdapter([
            to     : payment.payer.email,
            subject: "Pagamento Recebido",
            body   : body,
            isHTML : true
        ]))

        notificationService.save(new NotificationAdapter([
                title     : "Cobrança recebida",
                message   : "O usuário com email ${user.email} recebeu uma cobrança no dia ${DateFormatUtils.formatWithTime(payment.dateCreated)}",
                event     : NotificationEvent.PAYMENT_RECEIVED,
                link      : "http://localhost:8080/payment/show/${payment.id}",
                customer  : payment.customer
        ]))
    }

    public void onOverdue(Payment payment) {
        String body = groovyPageRenderer.render(
            template: "/payment/templates/email/payment-overdue",
            model: [payment: payment, baseUrl: System.getenv("BASE_URL")]
        )

        emailMessageService.save(new EmailAdapter([
            to     : payment.payer.email,
            subject: "Cobrança Vencida",
            body   : body,
            isHTML : true
        ]))

        notificationService.save(new NotificationAdapter([
                title     : "Cobrança vencida",
                message   : "Uma cobrança venceu no dia ${DateFormatUtils.formatWithTime(payment.dateCreated)}",
                event     : NotificationEvent.PAYMENT_OVERDUE,
                link      : "http://localhost:8080/payment/show/${payment.id}",
                customer  : payment.customer
        ]))
    }

}
