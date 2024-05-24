package com.mini.asaas.payer

import com.mini.asaas.enums.AlertType
import com.mini.asaas.repository.PayerRepository

class PayerController {

    PayerService payerService

    def index() {
        def payerList = PayerRepository.listAllNotDeleted()
        return [payerList: payerList]
    }

    def create() {}

    def save() {
        try {
            PayerAdapter adapter = new PayerAdapter(params)
            Payer payer = payerService.save(adapter)
            flash.message = "Pagador cadastrado com sucesso"
            flash.status = AlertType.SUCCESS.getValue()
            redirect(action: "show", id: payer.id)
        } catch (Exception e) {
            flash.message = e.getMessage()
            flash.status = AlertType.ERROR.getValue()
            render view: "create"
        }
    }

    def update() {
        try {
            PayerAdapter adapter = new PayerAdapter(params)
            Long id = params.id as Long
            Payer payer = payerService.update(adapter, id)
            flash.message = "Pagador atualizado com sucesso"
            flash.status = AlertType.SUCCESS.getValue()
            redirect(action: "show", id: payer.id)
        } catch (Exception e) {
            flash.message = e.getMessage()
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
