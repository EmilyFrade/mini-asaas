package core.dtos

import core.enums.AddressState

class CreateAddressDTO {

    String street
    String neighborhood
    String city
    AddressState state
    String zipCode
    Integer number
    String complement

    public CreateAddressDTO(Map params) {
        this.street = params.street
        this.neighborhood = params.neighborhood
        this.city = params.city
        this.state = AddressState.valueOf(params.state as String)
        this.zipCode = params.zipCode.toString().replace("-", "")
        this.complement = params.complement
        this.number = params.number ? params.number as Integer : null
    }

}
