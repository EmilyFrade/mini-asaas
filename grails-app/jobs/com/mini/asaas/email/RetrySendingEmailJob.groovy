package com.mini.asaas.email

import grails.gorm.transactions.Transactional

class RetrySendingEmailJob {

    SendEmailService sendEmailService

    static triggers = {
        cron name: 'ProcessRetrySendingEmailJobTrigger', cronExpression: "0 */2 * * * ?"
    }

    void execute() {
        List<EmailMessage> emailMessageList = EmailMessageRepository.query([
            status        : EmailStatus.PENDING,
            "attempts[lt]": EmailMessage.MAX_ATTEMPTS
        ]).list() as List<EmailMessage>

        for (EmailMessage emailMessage : emailMessageList) {
            sendEmail(emailMessage)
        }
    }

    @Transactional
    private void sendEmail(EmailMessage email) {
        sendEmailService.send(email)
    }
}
