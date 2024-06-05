package com.mini.asaas.user

import com.mini.asaas.enums.AlertType
import com.mini.asaas.exceptions.BusinessException
import com.mini.asaas.user.adapters.UpdateUserAdapter
import com.mini.asaas.user.adapters.UpdateUserPasswordAdapter
import grails.plugin.springsecurity.annotation.Secured

class UserController {

    UserService userService

    static allowedMethods = [show: "GET", update: "POST", updatePassword: "POST"]

    @Secured(["ROLE_ADMIN", "ROLE_SELLER"])
    def show() {
        try {
            User user = userService.show()

            render(view: "show", model: [user: user])
        } catch (Exception exception) {
            log.error(exception)

            redirect(uri: "/logout")
        }
    }

    @Secured(["ROLE_ADMIN", "ROLE_SELLER"])
    def update() {
        try {
            userService.update(new UpdateUserAdapter(params))
            flash.status = AlertType.SUCCESS.getValue()
            flash.message = "Usuário atualizado com sucesso!"

            redirect(action: "show")
        } catch (BusinessException businessException) {
            flash.message = businessException.message
            flash.status = AlertType.ERROR.getValue()

            redirect(action: "show")
        } catch (Exception exception) {
            log.error(exception)
            flash.message = "Ocorreu um erro durante a atualização dos dados, aguarde um momento e tente novamente."
            flash.status = AlertType.ERROR.getValue()

            redirect(action: "show")
        } finally {
            flash.section = "update"
        }
    }

    @Secured(["ROLE_ADMIN", "ROLE_SELLER"])
    def updatePassword() {
        try {
            userService.updatePassword(new UpdateUserPasswordAdapter(params))
            flash.status = AlertType.SUCCESS.getValue()
            flash.message = "Senha atualizada com sucesso!"

            redirect(action: "show")
        } catch (BusinessException businessException) {
            flash.message = businessException.message
            flash.status = AlertType.ERROR.getValue()

            redirect(action: "show")
        } catch (Exception exception) {
            log.error(exception)
            flash.message = "Ocorreu um erro durante a atualização dos dados, aguarde um momento e tente novamente."
            flash.status = AlertType.ERROR.getValue()

            redirect(action: "show")
        } finally {
            flash.section = "update-password"
        }
    }
}