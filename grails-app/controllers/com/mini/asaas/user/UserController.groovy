package com.mini.asaas.user

import com.mini.asaas.auth.AuthService
import com.mini.asaas.enums.AlertType
import com.mini.asaas.exceptions.BusinessException
import com.mini.asaas.user.adapters.LoginUserAdapter
import com.mini.asaas.user.adapters.SaveUserAdapter
import grails.plugin.springsecurity.annotation.Secured

class UserController {

    AuthService authService

    UserService userService

    static allowedMethods = [show: "GET"]

    @Secured(["ROLE_ADMIN", "ROLE_SELLER"])
    def show() {
        try {
            User user = userService.show()
            render(view: "show", model: [user: user])
        } catch (Exception e) {
            redirect(uri: "/logout")
        }
    }

    @Secured("permitAll")
    def create() {}

    @Secured("permitAll")
    def save() {
        try {
            userService.save(new SaveUserAdapter(params))
            authService.login(new LoginUserAdapter(params))

            redirect(controller: "customer", action: "create")
        } catch (BusinessException e) {
            flash.message = e.message
            flash.status = AlertType.ERROR.getValue()
            redirect(action: "create")
        } catch (Exception e) {
            e.printStackTrace()
            flash.message = "Ocorreu um erro durante o cadastro, aguarde um momento e tente novamente."
            flash.status = AlertType.ERROR.getValue()
            redirect(action: "create")
        }
    }

}
