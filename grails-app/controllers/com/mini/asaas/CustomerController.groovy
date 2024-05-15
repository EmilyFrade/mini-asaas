package com.mini.asaas

import core.dtos.AddressDTO
import core.dtos.CustomerDTO
import core.enums.AlertType
import core.exceptions.DomainException
import grails.validation.ValidationException

class CustomerController {

    CustomerService customerService

    def create() { }

    def save() {
        try {
            Customer customer = customerService.save(new CustomerDTO(params), new AddressDTO(params))
            flash.message = "Cliente cadastrado com sucesso"
            flash.status = AlertType.SUCCESS.getValue()
            redirect(action: "show", id: customer.id)
        } catch (DomainException e) {
            flash.message = e.message
            flash.status = e.alertType.getValue()
            render view: "create"
        } catch(ValidationException e) {
            render view: "create", model: [errors: e.getErrors()]
        } catch (Exception e) {
            flash.message = "Ocorreu um erro durante o cadastro, aguarde um momento e tente novamente."
            flash.status = AlertType.ERROR.getValue()
            render view: "create"
        }
    }

    def update() {
        try {
            Long id = params.id.toLong()
            Customer customer = customerService.update(id, new CustomerDTO(params))
            flash.message = "Cliente atualizado com sucesso"
            flash.status = AlertType.SUCCESS.getValue()
            flash.type = "customer"
            redirect(action: "show", id: customer.id)
        } catch (DomainException e) {
            flash.message = e.message
            flash.status = e.alertType.getValue()
            flash.type = "customer"
            redirect(action: "show", id: params.id)
        } catch(ValidationException e) {
            redirect(action: "show", id: params.id, model: [errors: e.getErrors()])
        } catch (Exception e) {
            flash.message = "Ocorreu um erro ao atualizar os dados, aguarde um momento e tente novamente."
            flash.status = AlertType.ERROR.getValue()
            flash.type = "customer"
            redirect(action: "show", id: params.id)
        }
    }

    def updateAddress() {
        try {
            Long id = params.id.toLong()
            Customer customer = customerService.updateAddress(id, new AddressDTO(params))
            flash.message = "Endereço atualizado com sucesso"
            flash.status = AlertType.SUCCESS.getValue()
            flash.type = "address"
            redirect(action: "show", id: customer.id)
        } catch (DomainException e) {
            flash.message = e.message
            flash.status = e.alertType.getValue()
            flash.type = "address"
            redirect(action: "show", id: params.id)
        } catch(ValidationException e) {
            redirect(action: "show", id: params.id, model: [errors: e.getErrors()])
        } catch (Exception e) {
            flash.message = "Ocorreu um erro ao atualizar o endereço, aguarde um momento e tente novamente."
            flash.status = AlertType.ERROR.getValue()
            flash.type = "address"
            redirect(action: "show", id: params.id)
        }
    }

    def show() {
        try {
            Long id = params.id.toLong()
            Customer customer = Customer.get(id)
            if (!customer) return redirect(action: "create")
            return [customer: customer]
        } catch (Exception e) {
            redirect(action: "create")
        }
    }
}
