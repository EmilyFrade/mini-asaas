package com.mini.asaas

import core.dtos.CreateAddressDTO
import core.dtos.CreateCustomerDTO
import core.dtos.UpdateCustomerDTO
import core.exceptions.EntityNotFoundException
import grails.validation.ValidationException

class CustomerController {

    CustomerService customerService

    def create() { }

    def save() {
        try {
            Customer customer = customerService.save(new CreateCustomerDTO(params), new CreateAddressDTO(params))
            flash.message = "Cliente cadastrado com sucesso"
            flash.status = "success"
            redirect(action: "show", id: customer.id)
        } catch(ValidationException e) {
            render view: "show", model: [errors: e.getErrors()]
        }
    }

    def update() {
        try {
            Long id = params.id.toLong()
            Customer customer = customerService.update(id, new UpdateCustomerDTO(params))
            flash.message = "Cliente atualizado com sucesso"
            flash.status = "success"
            redirect(action: "show", id: customer.id)
        } catch (EntityNotFoundException e) {
            flash.message = e.getMessage()
            flash.status = "error"
        } catch(ValidationException e) {
            render view: "show", model: [errors: e.getErrors()]
        }
    }

    def updateAddress() {
        try {
            Long id = params.id.toLong()
            Customer customer = customerService.updateAddress(id, new CreateAddressDTO(params))
            flash.message = "Endereço atualizado com sucesso"
            flash.status = "success"
            redirect(action: "show", id: customer.id)
        } catch (EntityNotFoundException e) {
            flash.message = e.getMessage()
            flash.status = "error"
        } catch(ValidationException e) {
            render view: "show", model: [errors: e.getErrors()]
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
