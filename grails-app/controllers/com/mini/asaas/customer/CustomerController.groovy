package com.mini.asaas.customer

import com.mini.asaas.enums.AlertType
import com.mini.asaas.exceptions.BusinessException
import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_USER"])
class CustomerController {

    CustomerService customerService

    @Secured("permitAll")
    def create() {}

    @Secured("permitAll")
    def save() {
        try {
            customerService.save(new CustomerAdapter(params as Map))
            flash.message = "Cliente salvo com sucesso."
            flash.status = AlertType.SUCCESS.getValue()
            redirect(controller: "auth", action: "login")
        } catch (BusinessException e) {
            flash.message = e.message
            flash.status = AlertType.ERROR.getValue()
            render(view: "create", model: [step: 3, addressOpened: true])
        } catch (Exception e) {
            flash.message = "Ocorreu um erro ao salvar os dados do cliente, tente novamente mais tarde."
            flash.status = AlertType.ERROR.getValue()
            render(view: "create", model: [step: 3, addressOpened: true])
        }
    }

}
