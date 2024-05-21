package com.mini.asaas.utils

import spock.lang.Specification

class CpfCnpjUtilsTest extends Specification {

    void "it should check if the size is valid for CPF"() {
        expect:
        CpfCnpjUtils.isCPF(input) == expected

        where:
        input                 | expected
        "123.456.789-01"      | true
        "12345678901"         | true
        "12.345.678/9012-34"  | false
        "123456789012345"     | false
        "invalid"             | false
    }

    void "it should check if the size is valid for CNPJ"() {
        expect:
        CpfCnpjUtils.isCNPJ(input) == expected

        where:
        input                 | expected
        "123.456.789-01"      | false
        "12345678901"         | false
        "12.345.678/9012-34"  | true
        "12345678901234"      | true
        "123456789012345"     | false
        "invalid"             | false
    }

    void "it should check if the CNPJ is valid"() {
        expect:
        CpfCnpjUtils.isValidCNPJ(input) == expected

        where:
        input                | expected
        null                 | false
        ""                   | false
        "11.111.111/1111-11" | false
        "11.444.777/0001-61" | true
        "11.444.777/0001-62" | false
        "11444777000162"     | false
        "11.444.777/0001-63" | false
        "48.176.416/0001-91" | true
        "48176416000191"     | true
        "48.176.416/0001-92" | false
        "invalid"            | false
    }

    void "it should check if the CPF is valid"() {
        expect:
        CpfCnpjUtils.isValidCPF(input) == expected

        where:
        input                | expected
        null                 | false
        ""                   | false
        "786.334.840-48"     | true
        "111.111.111-11"     | false
        "111.444.777-36"     | false
        "11144477736"        | false
        "111.444.777-37"     | false
        "788.590.730-90"     | true
        "78859073090"        | true
        "481.764.160-01"     | false
        "invalid"            | false
    }

    void "it should check if the value is an valid CPF or CNPJ"() {
        expect:
        CpfCnpjUtils.isValidCpfCnpj(input) == expected

        where:
        input                | expected
        null                 | false
        ""                   | false
        "11.111.111/1111-11" | false
        "11.444.777/0001-61" | true
        "invalid"            | false
        "786.334.840-48"     | true
        "111.111.111-11"     | false
    }

}
