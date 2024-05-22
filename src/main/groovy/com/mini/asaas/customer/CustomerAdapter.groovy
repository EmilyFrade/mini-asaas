package com.mini.asaas.customer

import com.mini.asaas.enums.PersonType
import com.mini.asaas.enums.address.AddressState

import java.text.DateFormat

class CustomerAdapter {

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

    String company

    String responsibleName

    String stateInscription

    CompanyType companyType

    public CustomerAdapter(Map params) {
        this.name = params.name
        this.email = params.email
        this.cpfCnpj = params.cpfCnpj
        this.phoneNumber = params.phoneNumber
        this.personType = PersonType.parseFromCpfCnpj(params.cpfCnpj as String)
        this.address = params.address
        this.addressNumber = params.addressNumber
        this.complement = params.complement
        this.province = params.province
        this.city = params.city
        this.state = AddressState.parseFromUFString(params.state as String)
        this.zipCode = params.zipCode
        this.birthDate = DateFormat.getDateInstance().parse(params.birthDate as String)
        this.company = params.company
        this.responsibleName = params.responsibleName
        this.stateInscription = params.stateInscription
        this.companyType = CompanyType.parseFromString(params.companyType as String)
    }

}
