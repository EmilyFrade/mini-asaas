package com.mini.asaas.email

class SendEmailMessageJob {

    EmailMessageService emailMessageService

    static triggers = {
        cron name: 'SendEmailMessageJobTrigger', cronExpression: "0/30 * * * * ?"
    }

    void execute() {
        try {
            emailMessageService.processPending()
        } catch (Exception exception) {
            log.error(exception)
        }
    }
}
