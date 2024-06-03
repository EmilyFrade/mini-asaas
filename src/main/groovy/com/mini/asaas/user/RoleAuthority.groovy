package com.mini.asaas.user

import com.mini.asaas.utils.MessageSourceUtils
import grails.util.Holders

enum RoleAuthority {
    ADMIN,
    USER

    public String getAuthority() {
        return Holders.grailsApplication.config.getProperty("security.basic.users.${name().toLowerCase()}.role")
    }

    public String getLabel() {
        return MessageSourceUtils.getEnumLabel(this)
    }

}