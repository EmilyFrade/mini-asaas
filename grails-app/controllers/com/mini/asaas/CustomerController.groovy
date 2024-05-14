package com.mini.asaas

import core.dtos.CreateAddressDTO
import core.dtos.CreateCustomerDTO
import core.dtos.UpdateCustomerDTO
import core.exceptions.EntityNoDataChangedException
import core.exceptions.EntityNotFoundException
import core.exceptions.EntityWithSamePropertyAlreadyExistsException
import grails.validation.ValidationException

class CustomerController {

    CustomerService customerService

    def create() { }

    def save() {
        try {
            Customer customer = customerService.save(new CreateCustomerDTO(params), new CreateAddressDTO(params))
            flash.message = "Cliente cadastrado com sucesso"
            flash.status = "success"
            flash.type = "customer"
            redirect(action: "show", id: customer.id)
        } catch(ValidationException e) {
            render view: "create", model: [errors: e.getErrors()]
        } catch (EntityWithSamePropertyAlreadyExistsException e) {
            flash.message = e.getMessage()
            flash.status = "error"
            flash.type = "customer"
            render view: "create"
        }
    }

    def update() {
        try {
            Long id = params.id.toLong()
            Customer customer = customerService.update(id, new UpdateCustomerDTO(params))
            flash.message = "Cliente atualizado com sucesso"
            flash.status = "success"
            flash.type = "customer"
            redirect(action: "show", id: customer.id)
        } catch (EntityNotFoundException | EntityWithSamePropertyAlreadyExistsException e) {
            flash.message = e.getMessage()
            flash.status = "error"
            flash.type = "customer"
            redirect(action: "show", id: params.id)
        } catch(ValidationException e) {
            redirect(action: "show", id: params.id, model: [errors: e.getErrors()])
        } catch (EntityNoDataChangedException e) {
            flash.message = e.getMessage()
            flash.status = "warning"
            flash.type = "customer"
            redirect(action: "show", id: params.id)
        }
    }

    def updateAddress() {
        try {
            Long id = params.id.toLong()
            Customer customer = customerService.updateAddress(id, new CreateAddressDTO(params))
            flash.message = "Endereço atualizado com sucesso"
            flash.status = "success"
            flash.type = "address"
            redirect(action: "show", id: customer.id)
        } catch (EntityNoDataChangedException e) {
            flash.message = e.getMessage()
            flash.status = "warning"
            flash.type = "address"
            redirect(action: "show", id: params.id)
        } catch(ValidationException e) {
            redirect(action: "show", id: params.id, model: [errors: e.getErrors()])
        } catch (EntityNotFoundException e) {
            flash.message = e.getMessage()
            flash.status = "error"
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
