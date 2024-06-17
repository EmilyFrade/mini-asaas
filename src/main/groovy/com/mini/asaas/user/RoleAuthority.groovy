package com.mini.asaas.user

import com.mini.asaas.utils.MessageSourceUtils

enum RoleAuthority {
    ADMIN,
    SELLER

    public Boolean isAdmin() {
        return this == ADMIN
    }

    public String getAuthority() {
        return "ROLE_${this.name()}"
    }

    public String getLabel() {
        return MessageSourceUtils.getEnumLabel(this)
    }

    public static RoleAuthority parseFromString(String value) {
        try {
            return valueOf(value.toUpperCase())
        } catch (Exception exception) {
            return null
        }
    }

}