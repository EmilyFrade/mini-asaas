package com.mini.asaas.base

import com.mini.asaas.shared.enums.PersonType
import com.mini.asaas.shared.objects.Address

abstract class BasePersonData extends BaseEntity {

    String name

    String email

    String cpfCnpj

    String phoneNumber

    PersonType personType

    Address address

    static constraints = {
        name blank: false
        email blank: false, email: true
        cpfCnpj blank: false, size: 11..14
        phoneNumber blank: false
        personType blank: false
        address nullable: false
    }

    static embedded = ["address"]

    static mapping = {
        tablePerHierarchy false
        email unique: true
        cpfCnpj unique: true
    }
}
