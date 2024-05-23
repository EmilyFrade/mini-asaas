package com.mini.asaas.customer

import com.mini.asaas.enums.PersonType
import com.mini.asaas.enums.address.AddressState
import com.mini.asaas.utils.DateFormatUtils
import com.mini.asaas.utils.StringUtils
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
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

    CompanyType companyType

    public CustomerAdapter(Map originalParams) {
        Map<String, String> params = StringUtils.parseParams(originalParams)
        if (!params) return
        this.name = params.name
        this.email = params.email
        this.cpfCnpj = StringUtils.removeNonNumeric(params.cpfCnpj) ?: null
        this.personType = PersonType.parseFromCpfCnpj(this.cpfCnpj)
        this.phoneNumber = StringUtils.removeNonNumeric(params.phoneNumber) ?: null
        this.address = params.address
        this.addressNumber = StringUtils.removeNonNumeric(params.addressNumber) ?: "S/N"
        this.complement = params.complement
        this.province = params.province
        this.city = params.city
        this.state = AddressState.parseFromUFString(params.state)
        this.zipCode = StringUtils.removeNonNumeric(params.zipCode) ?: null
        this.birthDate = DateFormatUtils.format(params.birthDate)
        this.companyType = CompanyType.parseFromString(params.companyType)
    }

}
