package core.dtos

import core.enums.AddressState
import core.valueobjects.Address

class AddressDTO {

    String street

    String neighborhood

    String city

    AddressState state

    String zipCode

    Integer number

    String complement

    public AddressDTO(Map params) {
        this.street = params.street
        this.neighborhood = params.neighborhood
        this.city = params.city
        this.state = AddressState.valueOf(params.state as String)
        this.zipCode = params.zipCode.toString().replace("-", "")
        this.complement = params.complement
        this.number = params.number ? params.number as Integer : null
    }

    public Address toAddress() {
        return new Address(
            street: this.street,
            neighborhood: this.neighborhood,
            city: this.city,
            state: this.state,
            zipCode: this.zipCode,
            number: this.number,
            complement: this.complement
        )
    }

}
