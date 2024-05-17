package core.dtos

import core.entities.Person
import core.enums.PersonType
import core.valueobjects.Address

class PayerDTO {
    String name
    String email
    String cpfCnpj
    String phoneNumber

    PersonType personType
    Address address

    public PayerDTO(Map params) {
        this.name = params.name
        this.email = params.email
        this.cpfCnpj = Person.cleanCpfCnpj(params.cpfCnpj as String)
        this.phoneNumber = params.phoneNumber
        this.personType = PersonType.fromCpfCnpj(this.cpfCnpj)
        this.address = new AddressDTO(params).toAddress()
    }
}
