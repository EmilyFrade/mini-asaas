package com.mini.asaas.user

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService

@Transactional
class UserService {

    SpringSecurityService springSecurityService

    public User show() {
        User user = springSecurityService.loadCurrentUser() as User
        if (!user) throw new RuntimeException("Usuário não encontrado")

        return user
    }
}
