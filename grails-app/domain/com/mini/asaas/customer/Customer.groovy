package com.mini.asaas.customer

import com.mini.asaas.base.BasePerson

class Customer extends BasePerson {

    String company

    String responsibleName

    String stateInscription

    CompanyType companyType

    static constraints = {
        company blank: false, nullable: true
        responsibleName blank: false, nullable: true
        stateInscription blank: false, nullable: true, maxSize: 18
        companyType blank: false, nullable: true
    }
}
