package com.mini.asaas.repository

import com.mini.asaas.customer.Customer
import com.mini.asaas.payer.Payer
import grails.gorm.transactions.Transactional
import org.grails.datastore.mapping.query.api.Criteria

@Transactional
class PayerRepository {

    public Criteria query(Map search) {
        Payer.createCriteria().list {
            if (!Boolean.valueOf(search.includeDeleted as String)) {
                eq('deleted', false)
            }

            if (search.containsKey('id')) {
                eq('id', search.id)
            }
        } as Criteria
    }

    static Payer findById(Long id) {
        Payer payer = Payer.get(id)
        if (!payer || payer.deleted) {
            return null
        }
        return payer
    }

    static void delete(Long id) {
        Payer entity = Payer.get(id)
        if (entity && !entity.deleted) {
            entity.deleted = true
            entity.save(flush: true)
        }
    }

    static List<Payer> listAllNotDeleted() {
        Payer.findAllByDeleted(false)
    }

    static List<Payer> listAllDeleted() {
        Payer.findAllByDeleted(true)
    }

    static Long countAllNotDeleted() {
        Payer.countByDeleted(false)
    }

    static Long countAllDeleted() {
        Payer.countByDeleted(true)
    }

    static Payer findByCpfCnpj(String cpfCnpj, Customer customer) {
        return Payer.findByCpfCnpjAndCustomer(cpfCnpj, customer)
    }

    static Payer findByEmail(String email, Customer customer) {
        return Payer.findByEmailAndCustomer(email, customer)
    }
}