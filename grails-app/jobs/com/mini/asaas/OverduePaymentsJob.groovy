package com.mini.asaas

import com.mini.asaas.payment.PaymentService

class OverduePaymentsJob {

    PaymentService paymentService

    static triggers = {
        cron name: 'ProcessOverduePaymentsJobTrigger', cronExpression: "0 0 0 * * ?"
    }

    def execute() {
        try {
            paymentService.setPaymentsAsOverdue()
        } catch (Exception exception) {
            log.error(exception)
        }
    }
}
