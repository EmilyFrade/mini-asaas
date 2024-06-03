package com.mini.asaas.customer

import com.mini.asaas.exceptions.BusinessException
import com.mini.asaas.user.SaveUserAdapter
import com.mini.asaas.user.UpdateUserAdapter
import com.mini.asaas.user.User
import com.mini.asaas.user.UserFunction
import com.mini.asaas.user.UserService
import com.mini.asaas.utils.DomainErrorUtils
import com.mini.asaas.validation.BusinessValidation
import grails.gorm.transactions.Transactional

@Transactional
class CustomerService {

    UserService userService

    public Customer save(CustomerAdapter customerAdapter) {
        Customer validatedCustomer = validate(customerAdapter, new Customer())
        if (validatedCustomer.hasErrors()) {
            throw new BusinessException(DomainErrorUtils.getFirstValidationMessage(validatedCustomer))
        }

        Customer customer = buildCustomer(customerAdapter, new Customer())

        customer.save(failOnError: true)

        userService.save(new SaveUserAdapter(
                name: customerAdapter.name,
                email: customerAdapter.email,
                password: customerAdapter.password,
                customer: customer,
                authority: UserFunction.ADMIN.getAuthority()
        ))

        return customer
    }

    public Customer show() {
        User user = userService.show()
        return user.customer
    }

    public Customer update(CustomerAdapter customerAdapter) {
        User user = userService.show()
        Customer customer = user.customer

        customer = validate(customerAdapter, customer)
        if (customer.hasErrors()) {
            throw new BusinessException(DomainErrorUtils.getFirstValidationMessage(customer))
        }

        customer = buildCustomer(customerAdapter, customer)
        customer.markDirty()
        customer.save(failOnError: true)

        if (user.email == customer.email) {
            userService.update(new UpdateUserAdapter(
                    name: customerAdapter.name,
                    email: customerAdapter.email
            ))
        }

        return customer
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
