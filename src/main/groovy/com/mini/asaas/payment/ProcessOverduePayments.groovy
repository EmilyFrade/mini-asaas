package com.mini.asaas.payment

import grails.gorm.transactions.Transactional
import org.quartz.Job
import org.quartz.JobExecutionContext

class ProcessOverduePayments implements Job {

    @Transactional
    void execute(JobExecutionContext context) {
        try {
            def searchParams = [
                    dueDate: new Date(),
                    status: PaymentStatus.PENDING
            ]

            List<Payment> overdueBillings = PaymentRepository.query(searchParams).list()

            overdueBillings.each { billing ->
                PaymentService.setPaymentAsOverdue(billing)
            }
        } catch (Exception exception) {
            log.error(exception)
        }
    }
}
