package com.mini.asaas.base

import com.mini.asaas.enums.PersonType
import com.mini.asaas.enums.address.AddressState

abstract class BasePerson extends BaseEntity {

    String name

    String email

    String cpfCnpj

    String phoneNumber

    PersonType personType

    String address

    String addressNumber

    String complement

    String province

    String city

    AddressState state

    String zipCode

    static constraints = {
        name blank: false
        email blank: false, email: true
        cpfCnpj blank: false, size: 11..14
        phoneNumber blank: false
        personType blank: false
        address blank: false
        addressNumber blank: false
        complement nullable: true
        province blank: false
        city blank: false
        state blank: false
        zipCode blank: false
    }

    static mappings = {
        email unique: true
        cpfCnpj unique: true
    }

}
