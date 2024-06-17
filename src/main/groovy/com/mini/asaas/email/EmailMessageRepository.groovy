package com.mini.asaas.email

import com.mini.asaas.repository.Repository
import org.grails.datastore.mapping.query.api.BuildableCriteria

class EmailMessageRepository implements Repository<EmailMessage, EmailMessageRepository> {
    @Override
    void buildCriteria() {
        addCriteria {
            if (search.containsKey("status")) {
                eq("status", search.status as EmailStatus)
            }
        }
    }

    @Override
    BuildableCriteria getBuildableCriteria() {
        return EmailMessage.createCriteria()
    }

    @Override
    List<String> listAllowedFilters() {
        return [
            "status"
        ]
    }
}
