package com.mini.asaas

import com.mini.asaas.user.Role
import com.mini.asaas.user.User
import com.mini.asaas.user.UserRole
import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional

class BootStrap {

    GrailsApplication grailsApplication

    def init = { servletContext ->
        createBasicRoles()
        createBasicUsers()
    }

    def destroy = {
    }

    @Transactional
    private void createBasicRoles() {
        String adminRole = grailsApplication.config.getProperty("security.basic.users.admin.role")
        String userRole = grailsApplication.config.getProperty("security.basic.users.user.role")

        if (!Role.findByAuthority(adminRole)) {
            new Role(authority: adminRole).save(failOnError: true)
        }

        if (!Role.findByAuthority(userRole)) {
            new Role(authority: userRole).save(failOnError: true)
        }
    }

    @Transactional
    private void createBasicUsers() {
        String adminEmail = grailsApplication.config.getProperty("security.basic.users.admin.email")

        if (User.findByEmail(adminEmail)) return

        String adminRole = grailsApplication.config.getProperty("security.basic.users.admin.role")
        String userRole = grailsApplication.config.getProperty("security.basic.users.user.role")
        String adminPassword = grailsApplication.config.getProperty("security.basic.users.admin.password")
        String adminName = grailsApplication.config.getProperty("security.basic.users.admin.name")
        User admin = new User(name: adminName, email: adminEmail, password: adminPassword)
        admin.save(failOnError: true)
        UserRole.create admin, Role.findByAuthority(adminRole)
        UserRole.create admin, Role.findByAuthority(userRole)
    }

}
