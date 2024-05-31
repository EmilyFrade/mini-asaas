package com.mini.asaas.repository

import com.mini.asaas.customer.Customer
import com.mini.asaas.payer.Payer
import grails.gorm.transactions.Transactional

@Transactional
class PayerRepository {

    public static Payer findById(Long id) {
        Payer.findById(id)
    }

    public static Payer findById(Long id, Boolean deleted) {
        Payer.findByIdAndDeleted(id, deleted)
    }

    public static Boolean existsByCpfCnpj(String cpfCnpj) {
        return Payer.countByCpfCnpj(cpfCnpj) > 0
    }

    public static Boolean existsByCpfCnpj(String cpfCnpj, Boolean deleted) {
        return Payer.countByCpfCnpjAndDeleted(cpfCnpj, deleted) > 0
    }

    public static Boolean existsByEmail(String email) {
        return Payer.countByEmail(email) > 0
    }

    public static Boolean existsByEmail(String email, Boolean deleted) {
        return Payer.countByEmailAndDeleted(email, deleted) > 0
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

    static Payer findByCpfCnpj(String cpfCnpj, Customer customer) {
        return Payer.findByCpfCnpjAndCustomer(cpfCnpj, customer)
    }

    static Payer findByEmail(String email, Customer customer) {
        return Payer.findByEmailAndCustomer(email, customer)
    }
}