package com.mini.asaas.shared.objects

import com.mini.asaas.shared.enums.AddressState

class Address {

    String street

    Integer number

    String complement

    String neighborhood

    String city

    AddressState state

    String zipCode

    static constraints = {
        street blank: false
        number blank: false, nullable: true, min: 1
        complement nullable: true
        neighborhood blank: false
        city blank: false
        state blank: false, matches: /[A-Z]{2}/
        zipCode blank: false, matches: /\d{8}/
    }

    static mappings = {
        state enumType: AddressState, sqlType: "varchar(2)"
        zipCode sqlType: "varchar(8)"
    }
}
