package com.mini.asaas

import com.mini.asaas.customer.CompanyType
import com.mini.asaas.customer.Customer
import com.mini.asaas.enums.PersonType
import com.mini.asaas.enums.address.AddressState
import com.mini.asaas.user.Role
import com.mini.asaas.user.RoleAuthority
import com.mini.asaas.user.User
import com.mini.asaas.user.UserRole
import com.mini.asaas.utils.DateFormatUtils
import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional

class BootStrap {

    GrailsApplication grailsApplication

    def init = { servletContext ->
        Map roles = createBasicRoles()
        Customer customer = createDefaultCustomer()
        createDefaultUser(customer, roles)
    }

    def destroy = {
    }

    @Transactional
    private Map<String, Role> createBasicRoles() {
        Map<String, Role> roles = [:]

        String adminRoleStr = RoleAuthority.ADMIN.getAuthority()
        String userRoleStr = RoleAuthority.USER.getAuthority()

        Role adminRole = Role.findByAuthority(adminRoleStr)
        Role userRole = Role.findByAuthority(userRoleStr)

        if (!adminRole) {
            adminRole = new Role(authority: adminRoleStr).save(failOnError: true)
        }

        if (!userRole) {
            userRole = new Role(authority: userRoleStr).save(failOnError: true)
        }

        roles.put("admin", adminRole)
        roles.put("user", userRole)

        return roles
    }

    @Transactional
    private void createDefaultUser(Customer customer, Map<String, Role> roles) {

        if (!customer || !roles) return
        if (User.findByEmail(customer.email)) return

        User admin = new User()
        admin.name = customer.name
        admin.email = customer.email
        admin.password = grailsApplication.config.getProperty("security.basic.users.admin.password")
        admin.customer = customer
        admin.save(failOnError: true)

        UserRole.create(admin, roles.get("admin"))
        UserRole.create(admin, roles.get("user"))
    }

    @Transactional
    private Customer createDefaultCustomer() {
        String cpfCnpj = grailsApplication.config.getProperty("security.basic.customers.default.cpfCnpj")
        Customer customer = Customer.findByCpfCnpj(cpfCnpj)
        if (customer) return customer

        String email = grailsApplication.config.getProperty("security.basic.customers.default.email")
        customer = Customer.findByEmail(email)
        if (customer) return customer

        customer = new Customer()
        customer.name = grailsApplication.config.getProperty("security.basic.customers.default.name")
        customer.email = email
        customer.cpfCnpj = cpfCnpj
        customer.personType = PersonType.parseFromCpfCnpj(cpfCnpj)
        customer.birthDate = DateFormatUtils.parseDateFromString("05/04/1990")
        customer.address = "Avenida Rolf Wiest"
        customer.addressNumber = "277"
        customer.complement = "Sala 814"
        customer.province = "Bom Retiro"
        customer.city = "Joinville"
        customer.state = AddressState.SC
        customer.zipCode = "89223005"
        customer.phoneNumber = "4734333333"
        customer.companyType = CompanyType.ASSOCIATION
        customer.save(failOnError: true)

        return customer
    }

}
