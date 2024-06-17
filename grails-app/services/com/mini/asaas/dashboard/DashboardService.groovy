package com.mini.asaas.dashboard

import com.mini.asaas.payer.Payer
import com.mini.asaas.payer.PayerRepository
import com.mini.asaas.payment.Payment
import com.mini.asaas.payment.PaymentRepository
import com.mini.asaas.user.User
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService

@Transactional
class DashboardService {

    SpringSecurityService springSecurityService

    public Map getSummary() {
        Long customerId = (springSecurityService.loadCurrentUser() as User).customerId
        List<Payment> paymentList = PaymentRepository.query([customerId: customerId]).list()
        List<Payment> overduePaymentList = paymentList.findAll { it.status.isOverdue() }
        List<Payment> receivedPaymentList = paymentList.findAll { it.status.isReceived() }
        List<Payer> payerList = PayerRepository.query([customerId: customerId]).list()

        Integer countDelinquentPayers = payerList.count { payer ->
            overduePaymentList.any { payment -> payment.payer.id == payer.id }
        }.toInteger()

        return [
            payments: [
                count  : [
                    total   : paymentList.size(),
                    overdue : overduePaymentList.size(),
                    received: receivedPaymentList.size()
                ],
                revenue: [
                    expected: paymentList.sum { it.value } ?: 0,
                    received: receivedPaymentList.sum { it.value } ?: 0
                ]
            ],
            payers  : [
                count: [
                    inDay     : payerList.size() - countDelinquentPayers,
                    delinquent: countDelinquentPayers
                ]
            ]
        ]
    }

}
