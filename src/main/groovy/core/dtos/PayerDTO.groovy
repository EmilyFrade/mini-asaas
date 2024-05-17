package core.dtos

import com.mini.asaas.Payer
import core.entities.Person
import core.enums.PersonType

class PayerDTO extends Person {
    public PayerDTO(Map params) {
        this.name = params.name
        this.email = params.email
        this.cpfCnpj = cleanCpfCnpj(params.cpfCnpj as String)
        this.phoneNumber = params.phoneNumber
        this.personType = PersonType.fromCpfCnpj(this.cpfCnpj)
        this.address = new AddressDTO(params)
    }
}
