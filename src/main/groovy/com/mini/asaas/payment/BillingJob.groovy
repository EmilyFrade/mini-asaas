package com.mini.asaas.payment

import grails.gorm.transactions.Transactional
import org.quartz.Job
import org.quartz.JobExecutionContext

class BillingJob implements Job {
    @Transactional
    void execute(JobExecutionContext context) {
        def overdueBillings = Payment.findAllByDueDateLessThanAndStatus(new Date(), PaymentStatus.PENDING)
        overdueBillings.each { billing ->
            billing.status = PaymentStatus.OVERDUE
            billing.save(flush: true)
        }
    }
}
