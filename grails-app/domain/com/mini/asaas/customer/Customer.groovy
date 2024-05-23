package com.mini.asaas.customer

import com.mini.asaas.base.BasePerson

class Customer extends BasePerson {

    String stateInscription

    CompanyType companyType

    static constraints = {
        stateInscription blank: false, nullable: true, maxSize: 18
        companyType blank: false, nullable: true
    }
}
