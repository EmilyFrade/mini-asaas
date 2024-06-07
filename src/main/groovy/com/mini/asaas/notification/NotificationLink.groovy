package com.mini.asaas.notification

import grails.validation.Validateable

class NotificationLink implements Validateable {

    String controllerName

    String actionName

    Long id

    static constraints = {
        controllerName blank: false
        actionName blank: false
        id nullable: true
    }

    public String getPath() {
        if (this.id) return "/${controllerName}/${actionName}/${id}"
        return "/${controllerName}/${actionName}"
    }
}
