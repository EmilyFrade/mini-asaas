package com.mini.asaas.user

import com.mini.asaas.utils.Utils

class UpdateUserAdapter {

    String name

    String email

    public UpdateUserAdapter(Map originalParams) {
        Map<String, String> params = Utils.normalizeParams(originalParams)
        if (!params) return
        this.name = params.name
        this.email = params.email
    }

}
