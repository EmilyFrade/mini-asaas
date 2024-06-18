package com.mini.asaas.user

import com.mini.asaas.customer.Customer
import com.mini.asaas.enums.AlertType
import com.mini.asaas.exceptions.BusinessException
import com.mini.asaas.user.adapters.SaveUserAdapter
import com.mini.asaas.user.adapters.UpdateUserAdapter
import com.mini.asaas.user.adapters.UpdateUserPasswordAdapter
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class UserController {

    UserService userService

    UserAdminService userAdminService

    SpringSecurityService springSecurityService

    static allowedMethods = [show: "GET"]

    def index() {
        List<User> userList = userAdminService.list()
        return [userList: userList]
    }

    def create() {}

    def save() {
        try {
            SaveUserAdapter adapter = new SaveUserAdapter(params)
            Customer customer = (springSecurityService.loadCurrentUser() as User).customer
            User user = userService.save(adapter, customer)

            flash.message = "Usuário cadastrado com sucesso"
            flash.status = AlertType.SUCCESS.getValue()

            redirect(action: "show", id: user.id)
        } catch (BusinessException exception) {
            flash.message = exception.getMessage()
            flash.status = AlertType.ERROR.getValue()

            render view: "create"
        } catch (Exception exception) {
            flash.message = "Ocorreu um erro durante o cadastro, aguarde um momento e tente novamente."
            flash.status = AlertType.ERROR.getValue()

            render view: "create"
        }
    }

    @Secured(["ROLE_ADMIN", "ROLE_SELLER"])
    def show() {
        try {
            User user = userService.loadLoggedUser()

            Long id = params.id as Long
            if(id) user = userAdminService.getUserIfAdminOfCustomer(id, user)

            render(view: "show", model: [user: user])
        } catch (Exception exception) {
            log.error(exception)
            redirect(uri: "/logout")
        }
    }

    @Secured(["ROLE_ADMIN", "ROLE_SELLER"])
    def update() {
        Long id = params.id as Long

        try {
            User user = userService.loadLoggedUser()

            if(id) user = userAdminService.getUserIfAdminOfCustomer(id, user)

            userService.update(new UpdateUserAdapter(params), user)
            flash.status = AlertType.SUCCESS.getValue()
            flash.message = "Usuário atualizado com sucesso!"

            redirect(action: "show", id: user.id)
        } catch (BusinessException businessException) {
            flash.message = businessException.message
            flash.status = AlertType.ERROR.getValue()

            redirect(action: "show", id: id)
        } catch (Exception exception) {
            log.error(exception)
            flash.message = "Ocorreu um erro durante a atualização dos dados, aguarde um momento e tente novamente."
            flash.status = AlertType.ERROR.getValue()

            redirect(action: "show", id: id)
        }
    }

    @Secured(["ROLE_ADMIN", "ROLE_SELLER"])
    def updatePassword() {
        Long id = params.id as Long

        try {
            User user = userService.loadLoggedUser()

            if(id) user = userAdminService.getUserIfAdminOfCustomer(id, user)

            userService.updatePassword(new UpdateUserPasswordAdapter(params), user)
            flash.status = AlertType.SUCCESS.getValue()
            flash.message = "Senha atualizada com sucesso!"

            redirect(action: "show", id: id)
        } catch (BusinessException businessException) {
            flash.message = businessException.message
            flash.status = AlertType.ERROR.getValue()

            redirect(action: "show", id: id)
        } catch (Exception exception) {
            log.error(exception)
            flash.message = "Ocorreu um erro durante a atualização dos dados, aguarde um momento e tente novamente."
            flash.status = AlertType.ERROR.getValue()

            redirect(action: "show", id: id)
        }
    }

    def delete() {
        try {
            Long id = params.id as Long
            userAdminService.delete(id)
            redirect(action: "index")
        } catch (BusinessException businessException) {
            flash.message = businessException.message
            flash.status = AlertType.ERROR.getValue()

            redirect(action: "index")
        } catch (Exception exception) {
            log.error(exception)
            flash.message = "Ocorreu um erro ao deletar usuário, aguarde um momento e tente novamente."
            flash.status = AlertType.ERROR.getValue()

            redirect(action: "index")
        }
    }

    def restore() {
        try {
            Long id = params.id as Long
            userAdminService.restore(id)
            redirect(action: "show", id: id)
        } catch (Exception exception) {
            log.error(exception)
            flash.message = "Ocorreu um erro ao restaurar usuário, aguarde um momento e tente novamente."
            flash.status = AlertType.ERROR.getValue()

            redirect(action: "index")
        }
    }

    @Secured(["ROLE_ADMIN", "ROLE_SELLER"])
    def logout() {
        redirect(uri: "/logout")
    }
}
