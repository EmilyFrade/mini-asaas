package com.mini.asaas.customer

import com.mini.asaas.exceptions.BusinessException
import com.mini.asaas.utils.DomainErrorUtils
import com.mini.asaas.validation.BusinessValidation
import grails.gorm.transactions.Transactional

@Transactional
class CustomerService {

    public Customer save(Map params) {
        CustomerAdapter customerAdapter = new CustomerAdapter(params)
        Customer customer = validateBeforeSave(customerAdapter)
        if (customer.hasErrors()) throw new BusinessException(DomainErrorUtils.getFirstValidationMessage(customer))

        customer = populateCustomer(customer, customerAdapter)
        customer.validate()

        if (customer.hasErrors()) throw new BusinessException(DomainErrorUtils.getFirstValidationMessage(customer))

        customer.save(failOnError: true)

        return customer
    }

    private Customer validateBeforeSave(CustomerAdapter customerAdapter) {
        Customer customer = new Customer()
        CustomerValidator validator = new CustomerValidator()

        Map requiredFields = [
            name: customerAdapter.name,
            email: customerAdapter.email,
            cpfCnpj: customerAdapter.cpfCnpj,
            phoneNumber: customerAdapter.phoneNumber,
            personType: customerAdapter.personType,
            address: customerAdapter.address,
            addressNumber: customerAdapter.addressNumber,
            province: customerAdapter.province,
            city: customerAdapter.city,
            state: customerAdapter.state,
            zipCode: customerAdapter.zipCode,
            birthDate: customerAdapter.birthDate
        ]

        validator
                .validateRequiredFields(requiredFields)
                .validateCpfCnpj(customerAdapter.cpfCnpj)
                .validateEmail(customerAdapter.email)
                .validateIfCpfCnpjExists(customerAdapter.cpfCnpj)
                .validateIfEmailExists(customerAdapter.email)
                .validateBirthDate(customerAdapter.birthDate)
                .validatePhoneNumber(customerAdapter.phoneNumber)
                .validateZipCode(customerAdapter.zipCode)

        BusinessValidation validationResult = validator.validationResult

        if (!validationResult.isValid()) {
            DomainErrorUtils.addBusinessRuleErrors(customer, validationResult.errors)
        }

        return customer
    }

    private Customer populateCustomer(Customer customer, CustomerAdapter customerAdapter) {
        customer.name = customerAdapter.name
        customer.email = customerAdapter.email
        customer.cpfCnpj = customerAdapter.cpfCnpj
        customer.phoneNumber = customerAdapter.phoneNumber
        customer.personType = customerAdapter.personType
        customer.address = customerAdapter.address
        customer.addressNumber = customerAdapter.addressNumber
        customer.complement = customerAdapter.complement
        customer.province = customerAdapter.province
        customer.city = customerAdapter.city
        customer.state = customerAdapter.state
        customer.zipCode = customerAdapter.zipCode
        customer.birthDate = customerAdapter.birthDate
        customer.companyType = customerAdapter.companyType
        return customer
    }
}
