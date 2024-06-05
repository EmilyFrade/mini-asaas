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
        Customer defaultCustomer = createDefaultCustomer()
        createDefaultAdminUser(roles, defaultCustomer)
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
            log.info("Role de administrador criada com sucesso")
        }

        if (!sellerRole) {
            sellerRole = new Role(authority: sellerAuthority.getAuthority()).save(failOnError: true)
            log.info("Role de vendedor criada com sucesso")
        }

        roles.put(adminAuthority, adminRole)
        roles.put(sellerAuthority, sellerRole)

        return roles
    }

    @Transactional
    private void createDefaultAdminUser(Map<RoleAuthority, Role> roles, Customer customer) {
        if (!customer) throw new RuntimeException("Não foi possível encontrar a conta administrativa padrão")

        String adminEmail = grailsApplication.config.getProperty("security.basic.users.admin.email")
        if (User.findByEmail(adminEmail)) return

        String adminPassword = grailsApplication.config.getProperty("security.basic.users.admin.password")

        User adminUser = new User()
        adminUser.name = customer.name
        adminUser.email = customer.email
        adminUser.password = adminPassword
        adminUser.customer = customer
        adminUser.save(failOnError: true)

        new UserRole(user: adminUser, role: roles.get(RoleAuthority.ADMIN)).save(failOnError: true)
        new UserRole(user: adminUser, role: roles.get(RoleAuthority.SELLER)).save(failOnError: true)

        log.info("Usuário administrador padrão criado com sucesso")
    }

    @Transactional
    private Customer createDefaultCustomer() {
        String cpfCnpj = grailsApplication.config.getProperty("security.basic.customers.default.cpfCnpj")
        Customer customer = Customer.findByCpfCnpj(cpfCnpj)
        if (customer) return customer

        String email = grailsApplication.config.getProperty("security.basic.users.admin.email")
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

        log.info("Conta administrativa padrão criada com sucesso")
        return customer
    }

}
