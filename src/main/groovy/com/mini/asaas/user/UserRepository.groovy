package com.mini.asaas.user

import com.mini.asaas.repository.Repository
import grails.compiler.GrailsCompileStatic
import org.grails.datastore.mapping.query.api.BuildableCriteria

@GrailsCompileStatic
class UserRepository implements Repository<User, UserRepository> {

    @Override
    void buildCriteria() {
        addCriteria {
            if (search.containsKey("email")) {
                eq("email", search.email)
            }

            if (search.containsKey("customerId")) {
                eq("customer.id", search.customerId)
            }
        }
    }

    @Override
    BuildableCriteria getBuildableCriteria() {
        return User.createCriteria()
    }

    @Override
    List<String> listAllowedFilters() {
        return [
            "email",
            "customerId"
        ]
    }
}
