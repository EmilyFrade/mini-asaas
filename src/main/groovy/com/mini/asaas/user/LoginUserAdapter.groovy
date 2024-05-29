package com.mini.asaas.user

import com.mini.asaas.utils.Utils
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class LoginUserAdapter {

    String email

    String password

    public LoginUserAdapter(Map originalParams) {
        Map<String, String> params = Utils.normalizeParams(originalParams)
        if (!params) return
        this.email = params.email
        this.password = params.password
    }

}
