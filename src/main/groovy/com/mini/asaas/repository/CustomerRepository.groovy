package com.mini.asaas.repository

import com.mini.asaas.customer.Customer
import grails.gorm.transactions.Transactional

@Transactional
class CustomerRepository {

    public static Customer findById(Long id) {
        return Customer.findByIdAndDeleted(id, false)
    }

    public static Boolean existsByEmail(String email) {
        return Customer.countByEmailAndDeleted(email, false) > 0
    }

    public static Boolean existsByCpfCnpj(String cpfCnpj) {
        return Customer.countByCpfCnpjAndDeleted(cpfCnpj, false) > 0
    }

    public static List<Customer> listAllNotDeleted() {
        return Customer.findAllByDeleted(false)
    }

}
