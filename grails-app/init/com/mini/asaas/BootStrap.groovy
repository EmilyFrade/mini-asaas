package com.mini.asaas

import com.mini.asaas.user.Role
import com.mini.asaas.user.RoleAuthority
import com.mini.asaas.user.User
import com.mini.asaas.user.UserRole
import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional

class BootStrap {

    GrailsApplication grailsApplication

    def init = { servletContext ->
        Map roles = createDefaultRoles()
        createDefaultAdminUser(roles)
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
    private void createDefaultAdminUser(Map<RoleAuthority, Role> roles) {
        String adminEmail = grailsApplication.config.getProperty("security.basic.users.admin.email")

        if (User.findByEmail(adminEmail)) return

        String adminPassword = grailsApplication.config.getProperty("security.basic.users.admin.password")
        String adminName = grailsApplication.config.getProperty("security.basic.users.admin.name")

        User admin = new User(name: adminName, email: adminEmail, password: adminPassword)
        admin.save(failOnError: true)

        new UserRole(user: admin, role: roles.get(RoleAuthority.ADMIN)).save(failOnError: true)
        new UserRole(user: admin, role: roles.get(RoleAuthority.SELLER)).save(failOnError: true)
    }

}
