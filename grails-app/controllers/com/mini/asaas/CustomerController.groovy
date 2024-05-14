package com.mini.asaas

import core.dtos.CreateAddressDTO
import core.dtos.CreateCustomerDTO
import core.dtos.UpdateCustomerDTO

class CustomerController {

    def customerService

    def index() { }

    def save() {
        try {
            def response = customerService.save(new CreateCustomerDTO(params), new CreateAddressDTO(params))

            if (response.success) {
                flash.message = "Cliente cadastrado com sucesso"
                flash.status = "success"
                redirect(action: "show", id: response.data.id)
                return
            }

            render view: "show", model: [errors: [customer: response.errors, address: null], customer: response.data]
        } catch (Exception e) {
            flash.message = "Erro ao cadastrar cliente"
            flash.status = "error"
        }
    }

    private Customer getCustomer(Long id) {
        Customer customer = Customer.get(id)

        if (!customer) {
            flash.message = "Cliente não encontrado"
            flash.status = "error"
            return null
        }

        return customer
    }

    def edit() {
        try {
            Long id = params.id.toLong()
            Customer customer = getCustomer(id)
            if (!customer) return redirect(action: "index")

            def response = customerService.update(customer, new UpdateCustomerDTO(params))

            if (response.success) {
                flash.message = "Cliente atualizado com sucesso"
                flash.status = "success"
                redirect(action: "show", id: response.data.id)
                return
            }

            render view: "show", model: [errors: [customer: response.errors, address: null], customer: response.data]

        } catch (Exception e) {
            flash.message = "Erro ao buscar cliente"
            flash.status = "error"
        }
    }

    def editAddress() {
        try {
            Long id = params.id.toLong()
            Customer customer = getCustomer(id)
            if (!customer) return redirect(action: "index")

            def response = customerService.updateAddress(customer, new CreateAddressDTO(params))

            if (response.success) {
                flash.message = "Endereço atualizado com sucesso"
                flash.status = "success"
                redirect(action: "show", id: response.data.id)
                return
            }

            render view: "show", model: [errors: [customer: null, address: response.errors], customer: response.data]

        } catch (Exception e) {
            flash.message = "Erro ao buscar cliente"
            flash.status = "error"
        }
    }

    def show() {
        try {
            Long id = params.id.toLong()
            Customer customer = getCustomer(id)
            if (!customer) return redirect(action: "index")
            return [customer: customer]
        } catch (Exception e) {
            flash.message = "Erro ao buscar cliente"
            flash.status = "error"
            redirect(action: "index")
        }
    }
}
