package com.mini.asaas.email

import grails.async.Promise
import grails.async.Promises
import grails.gorm.transactions.Transactional

@Transactional
class EmailMessageService {

    SendEmailService sendEmailService

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
        emailMessage.status = EmailStatus.PENDING
        emailMessage.attempts = 0
        emailMessage.save(failOnError: true)

        return emailMessage
    }

    public void processPending() {
        List<Long> emailMessageIdList = EmailMessageRepository.query([
            status: EmailStatus.PENDING
        ]).column("id").list([max: 100]) as List<Long>

        if (emailMessageIdList.isEmpty()) return

        List<Promise> promiseList = []
        emailMessageIdList.collate(10).each {
            List idList = it.collect()
            promiseList << Promises.task { sendEmailList(idList) }
        }

        Promises.waitAll(promiseList)
    }

    private void sendEmailList(List<Long> emailMessageIdList) {
        emailMessageIdList.each { emailMessageId ->
            EmailMessage.withNewTransaction { status ->
                try {
                    sendEmailService.send(emailMessageId)
                } catch (Exception exception) {
                    log.error("Error sending email message: ${emailMessageId}", exception)
                    status.setRollbackOnly()
                }
            }
        }
    }
}
