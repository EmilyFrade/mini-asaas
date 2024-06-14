package com.mini.asaas.user.adapters

import com.mini.asaas.utils.Utils
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class UpdateUserPasswordAdapter {

    String currentPassword

    String newPassword

    public UpdateUserPasswordAdapter(Map originalParams) {
        Map<String, String> params = Utils.normalizeParams(originalParams)
        if (!params) return
        this.currentPassword = params.currentPassword
        this.newPassword = params.newPassword
    }
}