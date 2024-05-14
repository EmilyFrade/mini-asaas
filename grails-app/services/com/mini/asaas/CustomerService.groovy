package com.mini.asaas

import core.dtos.CreateAddressDTO
import core.dtos.CreateCustomerDTO
import core.dtos.UpdateCustomerDTO
import core.enums.PersonType
import core.exceptions.EntityNotFoundException
import core.valueobjects.Address
import grails.compiler.GrailsCompileStatic
import grails.gorm.transactions.Transactional
import grails.validation.ValidationException

@GrailsCompileStatic
@Transactional
class CustomerService {

    public Customer save(CreateCustomerDTO customerDTO, CreateAddressDTO addressDTO) {

        Customer customer = new Customer()
        customer.name = customerDTO.name
        customer.email = customerDTO.email
        customer.cpfCnpj = customerDTO.cpfCnpj
        customer.phoneNumber = customerDTO.phoneNumber
        customer.personType = PersonType.fromCpfCnpj(customerDTO.cpfCnpj)
        customer.address = createAddress(addressDTO)

        if (!customer.validate()) {
            throw new ValidationException("Ocorreu um erro ao validar os dados", customer.errors)
        }

        customer.save(failOnError: true)

        return customer
    }

    public Customer update(Long id, UpdateCustomerDTO customerDTO) {

        Customer customer = Customer.get(id)

        if (!customer) {
            throw new EntityNotFoundException("Cliente não encontrado")
        }

        if (customerDTO.name) {
            customer.name = customerDTO.name
            customer.markDirty("name")
        }

        if (customerDTO.email) {
            customer.email = customerDTO.email
            customer.markDirty("email")
        }

        if (customerDTO.cpfCnpj) {
            customer.cpfCnpj = customerDTO.cpfCnpj
            customer.markDirty("cpfCnpj")

            PersonType personType = PersonType.fromCpfCnpj(customerDTO.cpfCnpj)
            if (customer.personType != personType) {
                customer.personType = personType
                customer.markDirty("personType")
            }
        }

        if (customerDTO.phoneNumber) {
            customer.phoneNumber = customerDTO.phoneNumber
            customer.markDirty("phoneNumber")
        }

        if (!customer.validate()) {
            throw new ValidationException("Ocorreu um erro ao validar os dados", customer.errors)
        }

        customer.save(failOnError: true)

        return customer
    }

    public Customer updateAddress(Long id, CreateAddressDTO addressDTO) {

        Customer customer = Customer.get(id)

        if (!customer) {
            throw new EntityNotFoundException("Cliente não encontrado")
        }

        if (addressDTO) {
            customer.address = createAddress(addressDTO)
            customer.markDirty("address")
        }

        if (!customer.validate()) {
            throw new ValidationException("Ocorreu um erro ao validar os dados de endereço", customer.errors)
        }

        return customer
    }

    private static Address createAddress(CreateAddressDTO addressDTO) {
        Address address = new Address()
        address.street = addressDTO.street
        address.neighborhood = addressDTO.neighborhood
        address.city = addressDTO.city
        address.state = addressDTO.state
        address.zipCode = addressDTO.zipCode
        address.number = addressDTO.number
        address.complement = addressDTO.complement
        return address
    }

}
