package core.dtos

class CreateAddressDTO {

    final String street
    final String neighborhood
    final String city
    final String ufState
    final String zipCode
    final Integer number
    final String complement

    CreateAddressDTO(Map<String, String> params) {
        this.street = params.street
        this.neighborhood = params.neighborhood
        this.city = params.city
        this.ufState = params.ufState
        this.zipCode = params.zipCode.replace("-", "")
        this.complement = params.complement
        this.number = params.number ? params.number?.toInteger() : null
    }

}
