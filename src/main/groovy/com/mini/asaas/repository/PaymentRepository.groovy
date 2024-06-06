package com.mini.asaas.repository

import com.mini.asaas.payment.Payment
import grails.gorm.transactions.Transactional

@Transactional
class PaymentRepository {

    public static Payment findById(Long id) {
        return Payment.findById(id)
    }

    public static Payment findById(Long id, Boolean deleted) {
        return Payment.findByIdAndDeleted(id, deleted)
    }

    public static List<Payment> listAll() {
        return Payment.findAll()
    }
}
