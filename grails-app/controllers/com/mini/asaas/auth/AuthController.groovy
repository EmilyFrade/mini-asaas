package com.mini.asaas.auth

import com.mini.asaas.enums.AlertType
import com.mini.asaas.exceptions.BusinessException
import com.mini.asaas.user.User
import com.mini.asaas.user.adapters.LoginUserAdapter
import grails.plugin.springsecurity.annotation.Secured

@Secured("permitAll")
class AuthController {

    AuthService authService

    static allowedMethods = [login: "GET", postLogin: "POST"]

    def login() {
        render(view: "login")
    }

    def postLogin() {
        try {
            User user = authService.login(new LoginUserAdapter(params))

            redirect(controller: "user", action: "show", id: user.id)
        } catch (BusinessException e) {
            flash.message = e.message
            flash.status = AlertType.ERROR.getValue()

            redirect(action: "login")
        } catch (Exception e) {
            flash.message = "Ocorreu um erro durante o login, aguarde um momento e tente novamente."
            flash.status = AlertType.ERROR.getValue()

            redirect(action: "login")
        }
    }
}
