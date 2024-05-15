package core.dtos

import core.valueobjects.Address

class AddressDTO extends Address{
    public AddressDTO(Map params) {
        this.street = params.street
        this.number = params.number as int
        this.complement = params.complement
        this.neighborhood = params.neighborhood
        this.city = params.city
        this.state = params.state
        this.zipCode = formatZipCode(params.zipCode as String)
    }
}
