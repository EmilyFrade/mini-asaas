package com.mini.asaas.customer

import com.mini.asaas.base.BasePerson
import com.mini.asaas.payer.Payer

class Customer extends BasePerson {

    CompanyType companyType

    static hasMany = [payers: Payer]

    static constraints = {
        companyType blank: false, nullable: true
    }
}
