package com.mini.asaas.payment

import com.mini.asaas.enums.AlertType
import com.mini.asaas.exceptions.BusinessException
import com.mini.asaas.payer.Payer
import com.mini.asaas.payer.PayerService
import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_SELLER"])
class PaymentController {

    PaymentService paymentService

    PayerService payerService

    def index() {
        List<Payment> paymentList = paymentService.list()
        return [paymentList: paymentList]
    }

    def create() {
        List<Payer> payerList = payerService.list()
        return [payerList: payerList]
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
            log.error(exception)
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
            log.error(exception)
            flash.message = "Ocorreu um erro ao atualizar os dados, aguarde um momento e tente novamente."
            flash.status = AlertType.ERROR.getValue()
            redirect(action: "show", id: params.id)
        }
    }

    def show() {
        try {
            Long id = params.id as Long
            Payment payment = paymentService.show(id)
            return [payment: payment]
        } catch (Exception exception) {
            log.error(exception)
            redirect(action: "index")
        }
    }

    def delete() {
        try {
            Long id = params.id as Long
            paymentService.delete(id)
            redirect(action: "index", id: id)
        } catch (Exception exception) {
            log.error(exception)
            redirect(action: "index")
        }
    }

    def restore() {
        try {
            Long id = params.id as Long
            paymentService.restore(id)
            redirect(action: "show", id: id)
        } catch (Exception exception) {
            log.error(exception)
            redirect(action: "index")
        }
    }
}
