package com.mini.asaas.payment

import grails.gorm.transactions.Transactional
import org.quartz.Job
import org.quartz.JobExecutionContext

class BillingJob implements Job {

    PaymentService paymentService

    @Transactional
    void execute(JobExecutionContext context) {
        paymentService.executeBillingJob()
    }
}
