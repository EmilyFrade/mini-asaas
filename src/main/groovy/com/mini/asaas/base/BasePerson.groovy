package com.mini.asaas.base

import com.mini.asaas.enums.PersonType

abstract class BasePerson extends BaseEntity {

    String name

    String email

    String cpfCnpj

    String phoneNumber

    PersonType personType

    String address

    String addressNumber

    String complement

    String neighborhood

    String city

    String state

    String zipCode

    static constraints = {
        name blank: false
        email blank: false, email: true, unique: true
        cpfCnpj blank: false, size: 11..14
        phoneNumber blank: false
        personType blank: false
        address blank: false
        addressNumber defaultValue: "S/N"
        complement nullable: true
        neighborhood blank: false
        city blank: false
        state blank: false, matches: /[A-Z]{2}/
        zipCode blank: false, matches: /\d{8}/
    }

}
