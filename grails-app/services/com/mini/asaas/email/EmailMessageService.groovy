package com.mini.asaas.email

import grails.gorm.transactions.Transactional

@Transactional
class EmailMessageService {

    private String fromEmailAddress = System.getenv("FROM_EMAIL_ADDRESS")

    private String fromEmailName = System.getenv("FROM_EMAIL_NAME")

    public EmailMessage save(EmailAdapter adapter) {
        EmailMessage emailMessage = new EmailMessage()
        emailMessage.from = this.fromEmailAddress
        emailMessage.fromName = this.fromEmailName
        emailMessage.to = adapter.to
        emailMessage.subject = adapter.subject
        emailMessage.body = adapter.body
        emailMessage.isHTML = adapter.isHTML
        emailMessage.sentDate = new Date()
        emailMessage.status = EmailStatus.PENDING
        emailMessage.attempts = 0
        emailMessage.save(failOnError: true)

        return emailMessage
    }
}
