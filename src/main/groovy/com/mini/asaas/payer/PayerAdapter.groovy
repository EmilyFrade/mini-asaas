package com.mini.asaas.payer

import com.mini.asaas.enums.PersonType
import com.mini.asaas.enums.address.AddressState
import com.mini.asaas.utils.StringUtils

class PayerAdapter {
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

    public PayerAdapter(Map params) {
        this.name = params.name
        this.email = params.email
        this.cpfCnpj = StringUtils.removeNonNumeric(params.cpfCnpj as String)
        this.phoneNumber = params.phoneNumber
        this.personType = PersonType.parseFromCpfCnpj(this.cpfCnpj)
        this.address = params.address
        this.addressNumber = params.addressNumber
        this.complement = params.complement
        this.province = params.province
        this.city = params.city
        this.state = params.state as AddressState
        this.zipCode = StringUtils.removeNonNumeric(params.zipCode as String)
    }
}