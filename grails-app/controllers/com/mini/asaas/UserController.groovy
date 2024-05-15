package com.mini.asaas

import core.exceptions.DomainException
import grails.plugin.springsecurity.annotation.Secured

@Secured("ROLE_USER")
class UserController {

    static allowedMethods = [show: "GET"]

    def show() {
        try {
            Long id = params.id.toLong()
            User user = User.get(id)
            if (!user) throw new DomainException("Usuário não encontrado.")
            render view: "show", model: [user: user]
        } catch (Exception e) {
            redirect uri: "/logout"
        }
    }
}
