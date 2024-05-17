package com.mini.asaas

import core.dtos.PayerDTO
import core.enums.AlertType
import grails.validation.ValidationException


class PayerController {
    PayerService payerService

    def index() {
        def payerList = PayerService.listAll()
        [payerList: payerList]
    }

    def create() {}

    def save() {
        try {
            PayerDTO dto = new PayerDTO(params)
            Payer payer = payerService.save(dto)
            flash.message = "Pagador cadastrado com sucesso"
            flash.status = AlertType.SUCCESS.getValue()
            redirect(action: "show", id: payer.id)
        } catch (Exception e) {
            flash.message = "Ocorreu um erro durante o cadastro, aguarde um momento e tente novamente."
            flash.status = AlertType.ERROR.getValue()
            render view: "create"
        }
    }

    def update() {
        try {
            PayerDTO dto = new PayerDTO(params)
            Payer payer = payerService.update(dto, params)
            flash.message = "Pagador atualizado com sucesso"
            flash.status = AlertType.SUCCESS.getValue()
            redirect(action: "show", id: payer.id)
        } catch (Exception e) {
            flash.message = "Ocorreu um erro ao atualizar os dados, aguarde um momento e tente novamente."
            flash.status = AlertType.ERROR.getValue()
            redirect(action: "show", id: params.id)
        }
    }

    def show() {
        try {
            Payer payer = Payer.get(params.id)
            if (!payer) return redirect(action: "create")
            return [payer: payer]
        } catch (Exception e) {
            redirect(action: "create")
        }
    }
}

