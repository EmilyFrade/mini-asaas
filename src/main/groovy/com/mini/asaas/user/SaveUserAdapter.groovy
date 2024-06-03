package com.mini.asaas.user

import com.mini.asaas.customer.Customer
import com.mini.asaas.utils.Utils

class SaveUserAdapter {

    String name

    String email

    String password

    Customer customer

    String authority

    public SaveUserAdapter(Map originalParams) {
        this.customer = originalParams.customer as Customer
        Map<String, String> params = Utils.normalizeParams(originalParams)
        if (!params) return
        this.name = params.name
        this.email = params.email
        this.password = params.password
        this.authority = params.authority
    }

}
