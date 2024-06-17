package com.mini.asaas.email

import com.sendgrid.Content
import com.sendgrid.Email
import com.sendgrid.Mail
import com.sendgrid.Method
import com.sendgrid.Personalization
import com.sendgrid.Request
import com.sendgrid.Response
import com.sendgrid.SendGrid
import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

@Slf4j
@CompileStatic
class SendGridSendEmailService implements SendEmailService, GrailsConfigurationAware {

    private static final Integer SENDGRID_SUCCESS_STATUS_CODE = 202

    String apiKey

    @Override
    public void send(Long emailMessageId) {
        EmailMessage emailMessage = EmailMessageRepository.get(emailMessageId)
        if (!emailMessage) return

        try {
            Mail mail = buildMail(emailMessage)
            SendGrid sendGrid = new SendGrid(this.apiKey)
            Request request = new Request()

            request.with {
                method = Method.POST
                endpoint = "mail/send"
                body = mail.build()
            }

            emailMessage.attempts++
            emailMessage.save(failOnError: true)

            Response response = sendGrid.api(request)
            if (response.statusCode != SENDGRID_SUCCESS_STATUS_CODE) {
                handleSendError(emailMessage, response.body)
            }

            emailMessage.status = EmailStatus.SENT
            emailMessage.sentDate = new Date()
            emailMessage.save(failOnError: true)
        } catch (IOException exception) {
            handleSendError(emailMessage, exception.message)
        }

    }

    @Override
    public void setConfiguration(Config config) {
        this.apiKey = config.getProperty("sendgrid.api")
        if (!this.apiKey) throw new IllegalStateException("A configuração 'sendgrid.api' não foi encontrada")
    }

    private Mail buildMail(EmailMessage emailMessage) {
        Personalization personalization = buildPersonalization(emailMessage)

        Email fromEmail = new Email()
        fromEmail.email = emailMessage.from

        Mail mail = new Mail()
        mail.from = fromEmail
        mail.addPersonalization(personalization)

        Content content = getContentOfEmail(emailMessage)
        mail.addContent(content)

        return mail
    }

    private Personalization buildPersonalization(EmailMessage emailMessage) {
        Personalization personalization = new Personalization()
        personalization.subject = emailMessage.subject

        Email toEmail = new Email(emailMessage.to)
        personalization.addTo(toEmail)

        return personalization
    }

    private Content getContentOfEmail(EmailMessage emailMessage) {
        if (!emailMessage.body) return null

        if (emailMessage.isHTML) return new Content("text/html", emailMessage.body)

        return new Content("text/plain", emailMessage.body)
    }

    private void handleSendError(EmailMessage emailMessage, String errorMessage) {
        log.error("Erro ao enviar e-mail: {} ({}/{})", errorMessage, emailMessage.attempts, EmailMessage.MAX_ATTEMPTS)
        if (emailMessage.attempts >= EmailMessage.MAX_ATTEMPTS) {
            emailMessage.status = EmailStatus.FAILED
            emailMessage.save(failOnError: true)
        }
    }
}
