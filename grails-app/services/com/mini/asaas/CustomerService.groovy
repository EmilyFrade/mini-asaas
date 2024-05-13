package com.mini.asaas

import core.dtos.CreateAddressDTO
import core.dtos.CreateCustomerDTO
import core.dtos.UpdateCustomerDTO
import core.enums.PersonType
import core.valueobjects.Address
import grails.gorm.transactions.Transactional

@Transactional
class CustomerService {

    private static Address createAddress(CreateAddressDTO addressDTO) {
        Address address = new Address()
        address.street = addressDTO.street
        address.neighborhood = addressDTO.neighborhood
        address.city = addressDTO.city
        address.ufState = addressDTO.ufState
        address.zipCode = addressDTO.zipCode
        address.number = addressDTO.number
        address.complement = addressDTO.complement
        return address
    }

    private static void updateProperty(Customer customer, String property, Object value) {
        if (value) {
            customer[property] = value
            customer.markDirty(property)
        }
    }

    def save(CreateCustomerDTO customerDTO, CreateAddressDTO addressDTO) {
        Customer customer = new Customer()
        customer.name = customerDTO.name
        customer.email = customerDTO.email
        customer.cpfCnpj = customerDTO.cpfCnpj
        customer.phoneNumber = customerDTO.phoneNumber
        customer.personType = PersonType.fromCpfCnpj(customerDTO.cpfCnpj)
        customer.address = createAddress(addressDTO)

        if (customer.validate()) {
            customer.save(failOnError: true)
        }

        return [data: customer, errors: customer.errors, success: !customer.hasErrors()]
    }

    def update(Customer customer, UpdateCustomerDTO customerDTO) {

        updateProperty(customer, "name", customerDTO.name)
        updateProperty(customer, "email", customerDTO.email)
        updateProperty(customer, "cpfCnpj", customerDTO.cpfCnpj)
        updateProperty(customer, "phoneNumber", customerDTO.phoneNumber)
        updateProperty(customer, "personType", PersonType.fromCpfCnpj(customerDTO.cpfCnpj))

        if (customer.validate()) {
            customer.save(failOnError: true)
        }

        return [data: customer, errors: customer.errors, success: !customer.hasErrors()]
    }

    def updateAddress(Customer customer, CreateAddressDTO addressDTO) {
        updateProperty(customer, "address", createAddress(addressDTO))

        if (customer.validate()) {
            customer.save(failOnError: true)
        }

        return [data: customer, errors: customer.errors, success: !customer.hasErrors()]
    }

}
