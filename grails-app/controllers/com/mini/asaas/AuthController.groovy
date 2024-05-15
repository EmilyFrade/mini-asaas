package com.mini.asaas

import core.dtos.LoginDTO
import core.exceptions.DomainException
import grails.plugin.springsecurity.annotation.Secured

@Secured("permitAll")
class AuthController {

    AuthService authService

    static allowedMethods = [login: "GET", doLogin: "POST"]

    /**
     * Renderiza a página de login.
     */
    def login() {
    }

    /**
     * Realiza o login do usuário e redireciona para a página de perfil do usuário.
     */
    def doLogin() {
        try {
            User user = authService.login(new LoginDTO(params))
            redirect(controller: "user", action: "show", id: user.id)
        } catch (DomainException e) {
            flash.message = e.message
            flash.status = "error"
            redirect(controller: "auth", action: "login")
        }
    }

}
