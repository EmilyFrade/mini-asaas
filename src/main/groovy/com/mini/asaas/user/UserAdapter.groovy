package com.mini.asaas.user


import com.mini.asaas.utils.Utils
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class UserAdapter {

    String name

    String email

    String password

    String role

    public UserAdapter(Map originalParams) {
        Map<String, String> params = Utils.normalizeParams(originalParams)
        if (!params) return
        this.role = params.role
        this.name = params.name
        this.email = params.email
        this.password = params.password
    }

}
