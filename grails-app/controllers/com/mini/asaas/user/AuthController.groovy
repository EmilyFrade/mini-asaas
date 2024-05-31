package com.mini.asaas.user

import com.mini.asaas.enums.AlertType
import com.mini.asaas.exceptions.BusinessException
import grails.plugin.springsecurity.annotation.Secured

@Secured("permitAll")
class AuthController {

    UserService userService

    static allowedMethods = [login: "GET", postLogin: "POST"]

    def login() {
        render(view: "login")
    }

    def postLogin() {
        try {
            User user = userService.login(new LoginUserAdapter(params))
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
