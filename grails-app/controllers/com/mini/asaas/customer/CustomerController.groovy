package com.mini.asaas.customer

import com.mini.asaas.enums.AlertType
import com.mini.asaas.exceptions.BusinessException

class CustomerController {

    CustomerService customerService

    def create() {}

    def postCreate() {
        try {
            Customer customer = customerService.save(new CustomerAdapter(params as Map))

            if (customer.hasErrors()) {
                flash.message = "Ocorreu um erro ao salvar os dados do cliente, corrija os campos e tente novamente."
                flash.status = AlertType.ERROR.getValue()
                render(view: "create", model: [errors: customer.errors, step: 3, addressOpened: true])
            }

            flash.message = "Cliente salvo com sucesso."
            flash.status = AlertType.SUCCESS.getValue()
            redirect(action: "show", id: customer.id)
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
