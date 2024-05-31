package com.mini.asaas.customer

import com.mini.asaas.exceptions.BusinessException
import com.mini.asaas.user.Role
import com.mini.asaas.user.User
import com.mini.asaas.user.UserFunction
import com.mini.asaas.user.UserRole
import com.mini.asaas.utils.DomainErrorUtils
import com.mini.asaas.validation.BusinessValidation
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService

@Transactional
class CustomerService {

    SpringSecurityService springSecurityService

    public Customer save(CustomerAdapter customerAdapter) {
        Customer validatedCustomer = validate(customerAdapter, new Customer())
        if (validatedCustomer.hasErrors()) {
            throw new BusinessException(DomainErrorUtils.getFirstValidationMessage(validatedCustomer))
        }

        Customer customer = buildCustomer(customerAdapter, new Customer())

        customer.save(failOnError: true)

        User user = new User()
        user.email = customerAdapter.email
        user.password = customerAdapter.password
        user.name = customerAdapter.name
        user.customer = customer
        user.save(failOnError: true)

        UserRole.create(user, Role.findByAuthority(UserFunction.ADMIN.getAuthority()))

        return customer
    }

    public Customer show() {
        User user = getCurrentUser()
        return user.customer
    }

    public Customer update(CustomerAdapter customerAdapter) {
        User user = getCurrentUser()
        Customer customer = user.customer

        customer = validate(customerAdapter, customer)
        if (customer.hasErrors()) {
            throw new BusinessException(DomainErrorUtils.getFirstValidationMessage(customer))
        }

        customer = buildCustomer(customerAdapter, customer)
        customer.markDirty()
        customer.save(failOnError: true)

        if (user.email == customer.email) {
            if (customer.email !== customerAdapter.email) {
                user.email = customerAdapter.email
                user.save(failOnError: true)
            }

            if (customer.name !== customerAdapter.name) {
                user.name = customerAdapter.name
                user.save(failOnError: true)
            }
        }

        return customer
    }

    private User getCurrentUser() {
        return springSecurityService.loadCurrentUser() as User
    }

    private Customer validate(CustomerAdapter customerAdapter, Customer customer) {
        CustomerValidator validator = new CustomerValidator()

        BusinessValidation validationResult = validator.validate(customerAdapter, customer)

        if (!validationResult.isValid()) {
            DomainErrorUtils.addBusinessRuleErrors(customer, validationResult.errors)
        }

        return customer
    }

    private Customer buildCustomer(CustomerAdapter customerAdapter, Customer customer) {
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
