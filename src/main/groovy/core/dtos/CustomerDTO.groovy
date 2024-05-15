package core.dtos

import com.mini.asaas.Customer
import core.enums.PersonType

class CustomerDTO {
    String name

    String email

    String cpfCnpj

    String phoneNumber

    public CustomerDTO(Map params) {
        this.name = params.name
        this.email = params.email
        this.cpfCnpj = params.cpfCnpj
        this.phoneNumber = params.phoneNumber
    }

    // Atualiza um Customer a partir dos dados do DTO
    public Customer updateCustomer(Customer customer) {
        customer.name = this.name
        customer.email = this.email
        customer.cpfCnpj = this.cpfCnpj
        customer.phoneNumber = this.phoneNumber
        customer.personType = PersonType.fromCpfCnpj(this.cpfCnpj)
        return customer
    }

    // Gerar um novo Customer a partir dos dados do DTO
    public Customer toCustomer() {
        Customer customer = new Customer()
        return this.updateCustomer(customer)
    }
}
