package com.mini.asaas.payment

import grails.gorm.transactions.Transactional
import org.quartz.Job
import org.quartz.JobExecutionContext

class ProcessOverduePayments implements Job {

    @Transactional
    void execute(JobExecutionContext context) {
        try {
            PaymentService.setPaymentsAsOverdue()
        } catch (Exception exception) {
            log.error(exception)
        }
    }
}
