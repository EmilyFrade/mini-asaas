package com.mini.asaas

import core.dtos.PayerDTO
import grails.validation.ValidationException

class PayerController {
    PayerService payerService

    def index() {
        def payerList = PayerService.listAll()
        [payerList: payerList]
    }

    def register() {}

    def save() {
        try {
            PayerDTO dto = new PayerDTO(params)
            Payer payer = payerService.save(dto)
            redirect(action: "show", id: payer.id)
        } catch (ValidationException e) {
            String errosMessage = e.errors.allErrors.defaultMessage.join(", ")
            render "Não foi criar um pagador, encontramos os seguintes erros: " + errosMessage
        }
    }

    def show() {
        Payer payer = Payer.get(params.id)

        if (payer) {
            return [payer: payer]
        }

        render "Pagador não encontrado"
    }
}

