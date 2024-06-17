package com.mini.asaas.payment

import com.mini.asaas.email.EmailAdapter
import com.mini.asaas.email.EmailMessageService
import com.mini.asaas.user.User
import grails.gorm.transactions.Transactional
import grails.gsp.PageRenderer
import org.springframework.beans.factory.annotation.Autowired

@Transactional
class PaymentEventNotificationService {

    EmailMessageService emailMessageService

    @Autowired
    PageRenderer pageRenderer

    public void onSave(Payment payment, User user) {
        String body = pageRenderer.render(
            template: "/payment/templates/email/payment-created",
            model: [payment: payment, user: user, baseUrl: System.getenv("BASE_URL")]
        )

        emailMessageService.save(new EmailAdapter([
            to     : payment.payer.email,
            subject: "Nova Cobrança",
            body   : body,
            isHTML : true
        ]))
    }

    public void onDelete(Payment payment, User user) {
        String body = pageRenderer.render(
            template: "/payment/templates/email/payment-deleted",
            model: [payment: payment, user: user, baseUrl: System.getenv("BASE_URL")]
        )

        emailMessageService.save(new EmailAdapter([
            to     : payment.payer.email,
            subject: "Cobrança Cancelada",
            body   : body,
            isHTML : true
        ]))
    }

    public void onReceive(Payment payment, User user) {
        String body = pageRenderer.render(
            template: "/payment/templates/email/payment-received",
            model: [payment: payment, user: user, baseUrl: System.getenv("BASE_URL")]
        )

        emailMessageService.save(new EmailAdapter([
            to     : payment.payer.email,
            subject: "Pagamento Recebido",
            body   : body,
            isHTML : true
        ]))
    }

    public void onOverdue(Payment payment) {
        String body = pageRenderer.render(
            template: "/payment/templates/email/payment-overdue",
            model: [payment: payment, baseUrl: System.getenv("BASE_URL")]
        )

        emailMessageService.save(new EmailAdapter([
            to     : payment.payer.email,
            subject: "Cobrança Vencida",
            body   : body,
            isHTML : true
        ]))
    }

}
