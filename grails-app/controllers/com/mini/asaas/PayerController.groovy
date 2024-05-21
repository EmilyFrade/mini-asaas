package com.mini.asaas

import com.mini.asaas.enums.AlertType

class PayerController {
    PayerService payerService

    def index() {
        def payerList = payerService.listAll()
        [payerList: payerList]
    }

    def create() {}

    def save() {
        try {
            Payer payer = payerService.save(params)
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
            Payer payer = payerService.update(params)
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

