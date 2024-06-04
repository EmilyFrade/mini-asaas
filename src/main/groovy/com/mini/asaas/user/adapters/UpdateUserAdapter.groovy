package com.mini.asaas.user.adapters

import com.mini.asaas.user.RoleAuthority
import com.mini.asaas.utils.Utils
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class UpdateUserAdapter {

    String name

    String email

    RoleAuthority roleAuthority

    public UpdateUserAdapter(Map originalParams) {
        Map<String, String> params = Utils.normalizeParams(originalParams)
        if (!params) return
        this.roleAuthority = RoleAuthority.parseFromString(params.roleAuthority)
        this.name = params.name
        this.email = params.email
    }

}
