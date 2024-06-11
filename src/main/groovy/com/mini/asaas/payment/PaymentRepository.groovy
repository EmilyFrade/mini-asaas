package com.mini.asaas.payment

import com.mini.asaas.repository.Repository
import grails.compiler.GrailsCompileStatic
import org.grails.datastore.mapping.query.api.BuildableCriteria

@GrailsCompileStatic
class PaymentRepository implements Repository<Payment, PaymentRepository> {

    @Override
    void buildCriteria() {
        addCriteria {
            if (search.containsKey("customerId")) {
                eq("customer.id", Long.valueOf(search.customerId.toString()))
            }

            if (search.containsKey("status")) {
                eq("status", PaymentStatus.valueOf(search.status as String))
            }

            if (search.containsKey("dueDate[lt]")) {
                lt("dueDate", search["dueDate[lt]"])
            }
        }

    }

    @Override
    BuildableCriteria getBuildableCriteria() {
        return Payment.createCriteria()
    }

    @Override
    List<String> listAllowedFilters() {
        return [
                "customerId",
                "status",
                "dueDate[lt]",
        ]
    }
}