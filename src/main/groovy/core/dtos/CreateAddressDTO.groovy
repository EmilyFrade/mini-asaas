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

    static constraints = {
        street nullable: false, blank: false
        neighborhood nullable: false, blank: false
        city nullable: false, blank: false
        ufState nullable: false, blank: false, matches: "[A-Z]{2}"
        zipCode nullable: false, blank: false, matches: "[0-9]{5}-[0-9]{3}"
        number blank: false, nullable: true, min: 1
        complement nullable: true, blank: true
    }

}
