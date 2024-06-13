package com.mini.asaas.payer

import com.mini.asaas.enums.PersonType
import com.mini.asaas.enums.address.AddressState
import com.mini.asaas.utils.DateFormatUtils
import com.mini.asaas.utils.StringUtils
import com.mini.asaas.utils.Utils

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

    Date birthDate

    public PayerAdapter(Map originalParams) {
        Map<String, String> params = Utils.normalizeParams(originalParams)
        if (!params) return
        this.name = params.name
        this.email = params.email
        this.cpfCnpj = StringUtils.removeNonNumeric(params.cpfCnpj as String) ?: null
        this.phoneNumber = StringUtils.removeNonNumeric(params.phoneNumber) ?: null
        this.personType = PersonType.parseFromCpfCnpj(this.cpfCnpj)
        this.address = params.address
        this.addressNumber = StringUtils.removeNonNumeric(params.addressNumber) ?: "S/N"
        this.complement = params.complement
        this.province = params.province
        this.city = params.city
        this.state = AddressState.parseFromUFString(params.state)
        this.zipCode = StringUtils.removeNonNumeric(params.zipCode) ?: null
        this.birthDate = DateFormatUtils.parseDateFromString(params.birthDate)
    }

}