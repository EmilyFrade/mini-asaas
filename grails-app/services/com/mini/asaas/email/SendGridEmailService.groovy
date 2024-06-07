package com.mini.asaas.email

import com.sendgrid.Content
import com.sendgrid.Email
import com.sendgrid.Mail
import com.sendgrid.Method
import com.sendgrid.Personalization
import com.sendgrid.Request
import com.sendgrid.SendGrid
import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

@Slf4j
@CompileStatic
class SendGridEmailService implements EmailService, GrailsConfigurationAware {

    String apiKey

    String from

    @Override
    void send(EmailAdapter adapter) {
        Mail mail = buildMail(adapter)
        SendGrid sendGrid = new SendGrid(this.apiKey)
        Request request = new Request()

        try {
            request.with {
                method = Method.POST
                endpoint = "mail/send"
                body = mail.build()
            }

            sendGrid.api(request)
        } catch (IOException e) {
            log.error(e.getMessage())
        }
    }

    @Override
    void setConfiguration(Config config) {
        this.apiKey = config.getProperty("sendgrid.api")
        if (!this.apiKey) throw new IllegalStateException("A configuração 'sendgrid.api' não foi encontrada")

        this.from = config.getProperty("sendgrid.from")
        if (!this.from) throw new IllegalStateException("A configuração 'sendgrid.from' não foi encontrada")
    }

    private Mail buildMail(EmailAdapter adapter) {
        Personalization personalization = buildPersonalization(adapter)

        Email fromEmail = new Email()
        fromEmail.email = this.from

        Mail mail = new Mail()
        mail.from = fromEmail
        mail.addPersonalization(personalization)

        Content content = getContentOfEmail(adapter)
        mail.addContent(content)

        return mail
    }

    private Personalization buildPersonalization(EmailAdapter adapter) {
        Personalization personalization = new Personalization()
        personalization.subject = adapter.subject

        Email toEmail = new Email(adapter.to)
        personalization.addTo(toEmail)

        return personalization
    }

    private Content getContentOfEmail(EmailAdapter adapter) {
        if (!adapter.body) return null

        if (adapter.isHTML) return new Content("text/html", adapter.body)

        return new Content("text/plain", adapter.body)
    }
}
