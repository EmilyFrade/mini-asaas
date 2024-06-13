package com.mini.asaas.notification

import com.mini.asaas.repository.Repository
import org.grails.datastore.mapping.query.api.BuildableCriteria

class NotificationRepository implements Repository<Notification, NotificationRepository> {
    @Override
    public void buildCriteria() {
        addCriteria {
            if (search.containsKey("customerId")) {
                eq("customer.id", search.customerId as Long)
            }

            if (search.containsKey("isRead")) {
                eq("isRead", search.isRead as Boolean)
            }
        }
    }

    @Override
    public BuildableCriteria getBuildableCriteria() {
        return Notification.createCriteria()
    }

    @Override
    public List<String> listAllowedFilters() {
        return [
            "customerId",
            "isRead"
        ]
    }
}
