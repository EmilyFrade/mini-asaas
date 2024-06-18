package com.mini.asaas

import com.mini.asaas.user.User
import grails.plugin.springsecurity.SpringSecurityService

class UserTagLib {

    static namespace = "user"

    SpringSecurityService springSecurityService

    def ifAdmin = { attrs, body ->
        User user = springSecurityService.loadCurrentUser() as User

        if (user.isAdmin()) out << body()
    }
}
