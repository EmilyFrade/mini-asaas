package com.mini.asaas.user.adapters

import com.mini.asaas.user.RoleAuthority
import com.mini.asaas.utils.Utils
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class SaveUserAdapter {

    private static final RoleAuthority DEFAULT_ROLE_AUTHORITY = RoleAuthority.ADMIN

    String name

    String email

    String password

    RoleAuthority roleAuthority

    public SaveUserAdapter(Map originalParams) {
        Map<String, String> params = Utils.normalizeParams(originalParams)
        if (!params) return
        this.roleAuthority = RoleAuthority.parseFromString(params.roleAuthority) ?: DEFAULT_ROLE_AUTHORITY
        this.name = params.name
        this.email = params.email
        this.password = params.password
    }
}
