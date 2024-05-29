package com.mini.asaas.repository

import com.mini.asaas.payer.Payer
import grails.gorm.transactions.Transactional

@Transactional
class PayerRepository {

    public static Payer findById(Long id) {
        Payer.findById(id)
    }

    public static Payer findById(Long id, Boolean bool) {
        Payer.findByIdAndDeleted(id, bool)
    }

    public static Boolean existsByCpfCnpj(String cpfCnpj) {
        return Payer.countByCpfCnpj(cpfCnpj) > 0
    }

    public static Boolean existsByCpfCnpj(String cpfCnpj, Boolean bool) {
        return Payer.countByCpfCnpjAndDeleted(cpfCnpj, bool) > 0
    }

    public static Boolean existsByEmail(String email) {
        return Payer.countByEmail(email) > 0
    }

    public static Boolean existsByEmail(String email, Boolean bool) {
        return Payer.countByEmailAndDeleted(email, bool) > 0
    }

    public static List<Payer> listAllNotDeleted() {
        return Payer.findAllByDeleted(false)
    }

    public static List<Payer> listAllDeleted() {
        return Payer.findAllByDeleted(true)
    }

    public static Long countAllNotDeleted() {
        return Payer.countByDeleted(false)
    }

    public static Long countAllDeleted() {
        return Payer.countByDeleted(true)
    }
}