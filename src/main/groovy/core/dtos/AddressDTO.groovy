package core.dtos

import core.valueobjects.Address

class AddressDTO {
    String street
    int number
    String complement
    String neighborhood
    String city
    String state
    String zipCode

    public AddressDTO(Map params) {
        this.street = params.street
        this.number = params.number as int
        this.complement = params.complement
        this.neighborhood = params.neighborhood
        this.city = params.city
        this.state = params.state
        this.zipCode = Address.formatZipCode(params.zipCode as String)
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
