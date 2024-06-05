package com.mini.asaas.customer

import com.mini.asaas.base.BasePerson

class Customer extends BasePerson {

    CompanyType companyType

    static constraints = {
        companyType nullable: true
    }
}
