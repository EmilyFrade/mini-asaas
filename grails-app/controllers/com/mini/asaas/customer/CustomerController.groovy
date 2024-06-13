package com.mini.asaas.customer

import com.mini.asaas.enums.AlertType
import com.mini.asaas.exceptions.BusinessException
import com.mini.asaas.user.User
import com.mini.asaas.user.adapters.SaveUserAdapter
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured

class CustomerController {

    CustomerService customerService

    SpringSecurityService springSecurityService

    @Secured("permitAll")
    def create() {}

    @Secured("permitAll")
    def save() {
        try {
            Customer customer = customerService.save(new CustomerAdapter(params), new SaveUserAdapter(params))
            flash.message = "Cliente salvo com sucesso."
            flash.status = AlertType.SUCCESS.getValue()

            redirect(action: "show", id: customer.id)
        } catch (BusinessException businessException) {
            flash.message = businessException.message
            flash.status = AlertType.ERROR.getValue()

            render(view: "create", model: [step: 3, addressOpened: true])
        } catch (Exception exception) {
            flash.message = "Ocorreu um erro ao salvar os dados do cliente, tente novamente mais tarde."
            flash.status = AlertType.ERROR.getValue()

            render(view: "create", model: [step: 3, addressOpened: true])
        }
    }

    @Secured(["ROLE_ADMIN", "ROLE_SELLER"])
    def show() {
        try {
            Customer customer = (springSecurityService.loadCurrentUser() as User).customer
            User user = springSecurityService.loadCurrentUser() as User

            return [customer: customer, user: user]
        } catch (Exception exception) {
            log.error(exception)
            redirect(uri: "/logout")
        }
    }

    @Secured(["ROLE_ADMIN"])
    def update() {
        try {
            CustomerAdapter adapter = new CustomerAdapter(params)
            customerService.update(adapter)

            flash.message = "Conta atualizada com sucesso"
            flash.status = AlertType.SUCCESS.getValue()

            redirect(action: "show")
        } catch (BusinessException exception) {
            flash.message = exception.getMessage()
            flash.status = AlertType.ERROR.getValue()

            redirect(action: "show")
        } catch (Exception exception) {
            log.error(exception)
            flash.message = "Ocorreu um erro ao atualizar os dados, aguarde um momento e tente novamente."
            flash.status = AlertType.ERROR.getValue()

            redirect(action: "show")
        }
    }
}
