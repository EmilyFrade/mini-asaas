package com.mini.asaas.shared.dtos

import com.mini.asaas.shared.enums.AddressState
import com.mini.asaas.shared.objects.Address
import com.mini.asaas.shared.utils.AddressZipCodeUtils

class AddressDTO {

    String street

    Integer number

    String complement

    String neighborhood

    String city

    AddressState state

    String zipCode

    public AddressDTO(Map params) {
        this.street = params.street
        this.number = (params.number as String).toInteger()
        this.complement = params.complement
        this.neighborhood = params.neighborhood
        this.city = params.city
        this.state = AddressState.valueOf(params.state as String)
        this.zipCode = AddressZipCodeUtils.clean(params.zipCode as String)
    }

    public Address toDomain() {
        return new Address(
            street: this.street,
            number: this.number,
            complement: this.complement,
            neighborhood: this.neighborhood,
            city: this.city,
            state: this.state,
            zipCode: this.zipCode
        )
    }

}
