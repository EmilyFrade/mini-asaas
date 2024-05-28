package com.mini.asaas.customer

import com.mini.asaas.enums.AlertType
import com.mini.asaas.exceptions.BusinessException

class CustomerController {

  CustomerService customerService

  def create() {}

  def postCreate() {
    try {
      Customer customer = customerService.save(new CustomerAdapter(params))

      if (customer.hasErrors()) {
        flash.message = "Ocorreu um erro ao salvar os dados do cliente, corrija os campos e tente novamente."
        flash.status = AlertType.ERROR.getValue()
        redirect(action: "create", model: [errors: customer.errors])
      }

      flash.message = "Cliente salvo com sucesso."
      flash.status = AlertType.SUCCESS.getValue()
      redirect(action: "show", id: customer.id)
    } catch (BusinessException e) {
      flash.message = e.message
      flash.status = AlertType.ERROR.getValue()
      redirect(action: "create")
    } catch (Exception e) {
      flash.message = "Ocorreu um erro ao salvar os dados do cliente, tente novamente mais tarde."
      flash.status = AlertType.ERROR.getValue()
      redirect(action: "create")
    }
  }

  def show() {
    try {
      Customer customer = customerService.show(params.id as Long)
      return [customer: customer]
    } catch (BusinessException e) {
      flash.message = e.message
      flash.status = AlertType.ERROR.getValue()
      redirect(action: "create")
    } catch (Exception e) {
      flash.message = "Ocorreu um erro ao consultar os dados do cliente, tente novamente mais tarde."
      flash.status = AlertType.ERROR.getValue()
      redirect(action: "create")
    }
  }

  def update() {
    try {
      Customer customer = customerService.update(new CustomerAdapter(params), params.id as Long)

      if (customer.hasErrors()) {
        flash.message = "Ocorreu um erro ao salvar os dados do cliente, corrija os campos e tente novamente."
        flash.status = AlertType.ERROR.getValue()
        render(view: "show", model: [errors: customer.errors])
      }

      flash.message = "Os dados do cliente foram atualizados com sucesso."
      flash.status = AlertType.SUCCESS.getValue()
      redirect(action: "show", id: customer.id)
    } catch (BusinessException e) {
      flash.message = e.message
      flash.status = AlertType.ERROR.getValue()
      render(view: "show", id: params.id)
    } catch (Exception e) {
      flash.message = "Ocorreu um erro ao salvar os dados do cliente, tente novamente mais tarde."
      flash.status = AlertType.ERROR
      render(view: "show", id: params.id)
    }
  }

}
