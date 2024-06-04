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
        Map roles = createDefaultRoles()
        User adminUser = createDefaultAdminUser(roles)
        createDefaultCustomer(adminUser)
    }

    def destroy = {
    }

    @Transactional
    private Map<RoleAuthority, Role> createDefaultRoles() {
        RoleAuthority adminAuthority = RoleAuthority.ADMIN
        RoleAuthority sellerAuthority = RoleAuthority.SELLER

        Map<RoleAuthority, Role> roles = [:]

        Role adminRole = Role.findByAuthority(adminAuthority.getAuthority())
        Role sellerRole = Role.findByAuthority(sellerAuthority.getAuthority())

        if (!adminRole) {
            adminRole = new Role(authority: adminAuthority.getAuthority()).save(failOnError: true)
        }

        if (!sellerRole) {
            sellerRole = new Role(authority: sellerAuthority.getAuthority()).save(failOnError: true)
        }

        roles.put(adminAuthority, adminRole)
        roles.put(sellerAuthority, sellerRole)

        return roles
    }

    @Transactional
    private User createDefaultAdminUser(Map<RoleAuthority, Role> roles) {
        String adminEmail = grailsApplication.config.getProperty("security.basic.users.admin.email")

        User user = User.findByEmail(adminEmail)
        if (user) return user

        String adminPassword = grailsApplication.config.getProperty("security.basic.users.admin.password")

        User adminUser = new User(email: adminEmail, password: adminPassword)
        adminUser.save(failOnError: true)

        new UserRole(user: adminUser, role: roles.get(RoleAuthority.ADMIN)).save(failOnError: true)
        new UserRole(user: adminUser, role: roles.get(RoleAuthority.SELLER)).save(failOnError: true)

        return adminUser
    }

    @Transactional
    private void createDefaultCustomer(User user) {
        if (!user) throw new RuntimeException("Não foi possível encontrar o usuário administrador da conta a ser criada")

        String cpfCnpj = grailsApplication.config.getProperty("security.basic.customers.default.cpfCnpj")
        Customer customer = Customer.findByCpfCnpj(cpfCnpj)
        if (customer) return

        String email = grailsApplication.config.getProperty("security.basic.users.admin.email")
        customer = Customer.findByEmail(email)
        if (customer) return

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

        user.name = customer.name
        user.customer = customer
        user.save(failOnError: true)
    }

}
