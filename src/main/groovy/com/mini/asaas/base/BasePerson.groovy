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

    Date birthDate

    static constraints = {
        email email: true
        cpfCnpj size: 11..14
        complement nullable: true
    }

    static mappings = {
        email unique: true
        cpfCnpj unique: true
    }

}
