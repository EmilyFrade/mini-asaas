package com.mini.asaas.customer

import com.mini.asaas.enums.PersonType
import com.mini.asaas.enums.address.AddressState
import com.mini.asaas.utils.DateFormatUtils
import spock.lang.Specification

class CustomerAdapterTest extends Specification {

    void "it should create a CustomerAdapter instance with the correct attributes"() {
        given:
            def params = [
                name            : "John Doe",
                email           : "johndoe@gmail.com",
                cpfCnpj         : "12.345.678/0001-90",
                phoneNumber     : "(11) 99999-9999",
                address         : "Rua dos Bobos",
                addressNumber   : "123",
                complement      : "Apto 123",
                province        : "SP",
                city            : "São Paulo",
                state           : "SP",
                zipCode         : "12345-678",
                birthDate       : "01/01/2000",
                stateInscription: "123456789123456789",
                companyType     : "mei"
            ]

        when:
            def customerAdapter = new CustomerAdapter(params)

        then:
            customerAdapter.name == "John Doe"
            customerAdapter.email == "johndoe@gmail.com"
            customerAdapter.cpfCnpj == "12345678000190"
            customerAdapter.phoneNumber == "11999999999"
            customerAdapter.personType == PersonType.LEGAL
            customerAdapter.address == "Rua dos Bobos"
            customerAdapter.addressNumber == "123"
            customerAdapter.complement == "Apto 123"
            customerAdapter.province == "SP"
            customerAdapter.city == "São Paulo"
            customerAdapter.state == AddressState.SP
            customerAdapter.zipCode == "12345678"
            customerAdapter.birthDate == DateFormatUtils.format("01/01/2000")
            customerAdapter.stateInscription == "123456789123456789"
            customerAdapter.companyType == CompanyType.MEI
    }

    void "it should set a all attributes to null when params is null"() {
        when:
            def customerAdapter = new CustomerAdapter(null)

        then:
            customerAdapter.name == null
            customerAdapter.email == null
            customerAdapter.cpfCnpj == null
            customerAdapter.phoneNumber == null
            customerAdapter.personType == null
            customerAdapter.address == null
            customerAdapter.addressNumber == null
            customerAdapter.complement == null
            customerAdapter.province == null
            customerAdapter.city == null
            customerAdapter.state == null
            customerAdapter.zipCode == null
            customerAdapter.birthDate == null
            customerAdapter.stateInscription == null
            customerAdapter.companyType == null
    }

    void "it should set a attributes to null or default values when attributes of params are null or empty"() {
        given:
            Map params = [
                name            : "  ",
                email           : "    ",
                cpfCnpj         : "",
                phoneNumber     : null,
                address         : "  ",
                addressNumber   : "",
                complement      : "",
                province        : "",
                city            : "",
                state           : "  ",
                zipCode         : "",
                birthDate       : "",
                stateInscription: "",
                companyType     : ""
            ]

        when:
            def customerAdapter = new CustomerAdapter(params)

        then:
            customerAdapter.name == null
            customerAdapter.email == null
            customerAdapter.cpfCnpj == null
            customerAdapter.phoneNumber == null
            customerAdapter.personType == null
            customerAdapter.address == null
            customerAdapter.addressNumber == "S/N"
            customerAdapter.complement == null
            customerAdapter.province == null
            customerAdapter.city == null
            customerAdapter.state == null
            customerAdapter.zipCode == null
            customerAdapter.birthDate == null
            customerAdapter.stateInscription == null
            customerAdapter.companyType == null
    }

    void "it should set addressNumber to 'S/N' when it is null or empty"() {
        expect:
            new CustomerAdapter([addressNumber: ""]).addressNumber == "S/N"
            new CustomerAdapter([addressNumber: null]).addressNumber == "S/N"
    }

    void "it should set birthDate to null when it is null or empty"() {
        expect:
            new CustomerAdapter([birthDate: ""]).birthDate == null
            new CustomerAdapter([birthDate: null]).birthDate == null
    }

    void "it should set stateInscription to null when it is null or empty or not contain digits"() {
        expect:
            new CustomerAdapter([stateInscription: ""]).stateInscription == null
            new CustomerAdapter([stateInscription: null]).stateInscription == null
            new CustomerAdapter([stateInscription: "abc"]).stateInscription == null
    }

    void "it should set companyType to null when it is null or empty or not a existent CompanyType"() {
        expect:
            new CustomerAdapter([companyType: ""]).companyType == null
            new CustomerAdapter([companyType: null]).companyType == null
            new CustomerAdapter([companyType: "abc"]).companyType == null
    }

    void "it should set personType to null when cpfCnpj is null or empty or not is CPF or CNPJ"() {
        expect:
            new CustomerAdapter([cpfCnpj: ""]).personType == null
            new CustomerAdapter([cpfCnpj: null]).personType == null
            new CustomerAdapter([cpfCnpj: "abc"]).personType == null
    }

    void "it should set personType to NATURAL when cpfCnpj is a CPF"() {
        expect:
            new CustomerAdapter([cpfCnpj: "123.456.789-09"]).personType == PersonType.NATURAL
            new CustomerAdapter([cpfCnpj: "12345678909"]).personType == PersonType.NATURAL
    }

    void "it should set personType to LEGAL when cpfCnpj is a CNPJ"() {
        expect:
            new CustomerAdapter([cpfCnpj: "12.345.678/0001-90"]).personType == PersonType.LEGAL
            new CustomerAdapter([cpfCnpj: "12345678000190"]).personType == PersonType.LEGAL
    }

    void "it should set phoneNumber to null when it is null or empty or not contain digits"() {
        expect:
            new CustomerAdapter([phoneNumber: ""]).phoneNumber == null
            new CustomerAdapter([phoneNumber: null]).phoneNumber == null
            new CustomerAdapter([phoneNumber: "abc"]).phoneNumber == null
    }

    void "it should set zipCode to null when it is null or empty or not contain digits"() {
        expect:
            new CustomerAdapter([zipCode: ""]).zipCode == null
            new CustomerAdapter([zipCode: null]).zipCode == null
            new CustomerAdapter([zipCode: "abc"]).zipCode == null
    }

}
