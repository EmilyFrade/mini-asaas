package com.mini.asaas.repository

import com.mini.asaas.payer.Payer
import grails.gorm.transactions.Transactional

@Transactional
class PayerRepository {

    static Payer findById(Long id, Boolean bool) {
        Payer.findByIdAndDeleted(id, bool)
    }

    static Payer findById(Long id) {
        Payer.findById(id)
    }

    static List<Payer> listAllNotDeleted() {
        return Payer.findAllByDeleted(false)
    }

    static List<Payer> listAllDeleted() {
        return Payer.findAllByDeleted(true)
    }

    static Long countAllNotDeleted() {
        return Payer.countByDeleted(false)
    }

    static Long countAllDeleted() {
        return Payer.countByDeleted(true)
    }
}