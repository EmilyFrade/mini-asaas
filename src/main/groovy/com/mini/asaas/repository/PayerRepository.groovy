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

    public static Boolean existsByCpfCnpj(String cpfCnpj, Customer customer) {
        return Payer.countByCpfCnpjAndCustomer(cpfCnpj, customer)
    }

    public static Boolean existsByCpfCnpj(String cpfCnpj, Customer customer, Boolean deleted) {
        return Payer.countByCpfCnpjAndCustomerAndDeleted(cpfCnpj, customer, deleted)
    }

    public static Boolean existsByEmail(String email, Customer customer) {
        return Payer.countByEmailAndCustomer(email, customer)
    }

    public static Boolean existsByEmail(String email, Customer customer, Boolean deleted) {
        return Payer.countByEmailAndCustomerAndDeleted(email, customer, deleted)
    }

    public static List<Payer> listAllNotDeleted() {
        return Payer.findAllByDeleted(false)
    }

    public static List<Payer> listAllDeleted() {
        return Payer.findAllByDeleted(true)
    }
}