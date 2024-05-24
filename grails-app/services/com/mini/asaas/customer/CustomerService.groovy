package com.mini.asaas.customer

import com.mini.asaas.exceptions.BusinessException
import com.mini.asaas.utils.DomainErrorUtils
import com.mini.asaas.validation.BusinessValidation
import grails.gorm.transactions.Transactional

@Transactional
class CustomerService {

    public Customer save(CustomerAdapter customerAdapter) {
        Customer validatedCustomer = validate(customerAdapter)
        if (validatedCustomer.hasErrors()) {
            throw new BusinessException(DomainErrorUtils.getFirstValidationMessage(validatedCustomer))
        }

        Customer customer = buildCustomer(customerAdapter)
        customer.save(failOnError: true)

        return customer
    }

    private Customer validate(CustomerAdapter customerAdapter) {
        Customer validatedCustomer = new Customer()
        CustomerValidator validator = new CustomerValidator()

        validator
            .validateCpfCnpj(customerAdapter.cpfCnpj)
            .validateEmail(customerAdapter.email)
            .validateIfCpfCnpjExists(customerAdapter.cpfCnpj)
            .validateIfEmailExists(customerAdapter.email)
            .validateBirthDate(customerAdapter.birthDate)
            .validatePhoneNumber(customerAdapter.phoneNumber)
            .validateZipCode(customerAdapter.zipCode)

        BusinessValidation validationResult = validator.validationResult

        if (!validationResult.isValid()) {
            DomainErrorUtils.addBusinessRuleErrors(validatedCustomer, validationResult.errors)
        }

        return validatedCustomer
    }

    private Customer buildCustomer(CustomerAdapter customerAdapter) {
        Customer customer = new Customer()
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
