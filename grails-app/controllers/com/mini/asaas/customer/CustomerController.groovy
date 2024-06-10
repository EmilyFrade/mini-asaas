package com.mini.asaas.customer

import com.mini.asaas.enums.AlertType
import com.mini.asaas.exceptions.BusinessException
import grails.plugin.springsecurity.annotation.Secured

class CustomerController {

    CustomerService customerService

    @Secured("permitAll")
    def create() {}

    @Secured("permitAll")
    def save() {
        try {
            Customer customer = customerService.save(new CustomerAdapter(params))
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

}
