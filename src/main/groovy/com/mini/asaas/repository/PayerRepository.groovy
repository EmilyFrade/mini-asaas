package com.mini.asaas.repository

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