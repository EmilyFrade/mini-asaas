package com.mini.asaas.enums

import spock.lang.Specification

class PersonTypeTest extends Specification {

    void "it should check if PersonType is natural"() {
        expect:
            PersonType.LEGAL.isNatural() == false
            PersonType.NATURAL.isNatural() == true
    }

    void "it should check if PersonType is legal"() {
        expect:
            PersonType.LEGAL.isLegal() == true
            PersonType.NATURAL.isLegal() == false
    }

    void "it should return correct PersonType according to string values"() {
        expect:
            PersonType.fromString(input) == expected

        where:
            input           | expected
            "NATURAL"       | PersonType.NATURAL
            "natural"       | PersonType.NATURAL
            "LEGAL"         | PersonType.LEGAL
            "legal"         | PersonType.LEGAL
    }

    void "it should throw an exception when trying to get the PersonType with a invalid String value"() {
        when:
            PersonType.fromString("INVALID")

        then:
            thrown(IllegalArgumentException)
    }

    void "it should return correct PersonType according to CPF or CNPJ values"() {
        expect:
            PersonType.parseFromCpfCnpj(input) == expected

        where:
            input                   | expected
            "123.456.789-01"        | PersonType.NATURAL
            "12345678901"           | PersonType.NATURAL
            "12.345.678/9012-34"    | PersonType.LEGAL
            "12345678901234"        | PersonType.LEGAL
    }

    void "it should throw an exception when trying to get the PersonType with a value that is neither CPF nor CNPJ"() {
        when:
            PersonType.parseFromCpfCnpj("INVALID")
        then:
            thrown(IllegalArgumentException)
    }



}
