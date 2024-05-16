package com.mini.asaas.shared.objects

class Address {

    String street

    Integer number

    String complement

    String neighborhood

    String city

    String state

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
}
