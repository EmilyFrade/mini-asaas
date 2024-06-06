package com.mini.asaas.payment

import com.mini.asaas.enums.AlertType
import com.mini.asaas.exceptions.BusinessException
import com.mini.asaas.repository.CustomerRepository
import com.mini.asaas.repository.PayerRepository
import com.mini.asaas.repository.PaymentRepository
import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_USER"])
class PaymentController {

    PaymentService paymentService

    def index() {
        def paymentList = PaymentRepository.listAll()
        return [paymentList: paymentList]
    }

    def create() {
        def customerList = CustomerRepository.listAllNotDeleted()
        def payerList = PayerRepository.listAllNotDeleted()

        return [customerList: customerList, payerList: payerList]
    }

    def save() {
        try {
            PaymentAdapter adapter = new PaymentAdapter(params)
            Payment payment = paymentService.save(adapter)
            flash.message = "Cobrança criada com sucesso"
            flash.status = AlertType.SUCCESS.getValue()
            redirect(action: "show", id: payment.id)
        } catch (BusinessException exception) {
            flash.message = exception.getMessage()
            flash.status = AlertType.ERROR.getValue()
            render view: "create"
        } catch (Exception exception) {
            flash.message = "Ocorreu um erro durante a criação, aguarde um momento e tente novamente."
            flash.status = AlertType.ERROR.getValue()
            render view: "create"
        }
    }

    def update() {
        try {
            PaymentAdapter adapter = new PaymentAdapter(params)
            Long id = params.id as Long
            Payment payment = paymentService.update(adapter, id)
            flash.message = "Cobrança atualizada com sucesso"
            flash.status = AlertType.SUCCESS.getValue()
            redirect(action: "show", id: payment.id)
        } catch (BusinessException businessException) {
            flash.message = businessException.getMessage()
            flash.status = AlertType.ERROR.getValue()
            redirect(action: "show", id: params.id)
        } catch (Exception exception) {
            flash.message = "Ocorreu um erro ao atualizar os dados, aguarde um momento e tente novamente."
            flash.status = AlertType.ERROR.getValue()
            redirect(action: "show", id: params.id)
        }
    }

    def show() {
        def payerList = PayerRepository.listAllNotDeleted()

        try {
            Long id = params.id as Long
            Payment payment = paymentService.show(id)
            return [payment: payment, payerList: payerList]
        } catch (Exception exception) {
            redirect(action: "index")
        }
    }

    def delete() {
        try {
            Long id = params.id as Long
            paymentService.delete(id)
            redirect(action: "index", id: id)
        } catch (Exception exception) {
            redirect(action: "index")
        }
    }

    def restore() {
        try {
            Long id = params.id as Long
            paymentService.restore(id)
            redirect(action: "show", id: id)
        } catch (Exception exception) {
            redirect(action: "index")
        }
    }
}
