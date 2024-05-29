package com.mini.asaas.payer

import com.mini.asaas.enums.AlertType
import com.mini.asaas.exceptions.BusinessException
import com.mini.asaas.repository.PayerRepository
import com.mini.asaas.utils.StringUtils

class PayerController {

    PayerService payerService

    def index() {
        def payerList = PayerRepository.listAllNotDeleted()
        return [payerList: payerList]
    }

    def create() {}

    def restore() {
        def payerList = PayerRepository.listAllDeleted()
        return [payerList: payerList]
    }

    def save() {
        try {
            PayerAdapter adapter = new PayerAdapter(params)
            Payer payer = payerService.save(adapter)
            flash.message = "Pagador cadastrado com sucesso"
            flash.status = AlertType.SUCCESS.getValue()
            redirect(action: "show", id: payer.id)
        } catch (BusinessException e) {
            flash.code = e.code
            flash.message = e.getMessage()
            flash.status = AlertType.ERROR.getValue()
            render view: "create"
        } catch (Exception e) {
            flash.message = "Ocorreu um erro durante o cadastro, aguarde um momento e tente novamente."
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
        } catch (BusinessException e) {
            flash.code = e.code
            flash.message = e.getMessage()
            flash.status = AlertType.ERROR.getValue()
            redirect(action: "show", id: params.id)
        } catch (Exception e) {
            flash.message = "Ocorreu um erro ao atualizar os dados, aguarde um momento e tente novamente."
            flash.status = AlertType.ERROR.getValue()
            redirect(action: "show", id: params.id)
        }
    }

    def show() {
        try {
            Long id = params.id as Long

            if (!id) {
                def payerByEmail = Payer.findByEmail(params.email as String)
                if (payerByEmail) id = payerByEmail.id
            }

            if (!id) {
                def payerByCpfCnpj = Payer.findByCpfCnpj(StringUtils.removeNonNumeric(params.cpfCnpj as String))
                if (payerByCpfCnpj) id = payerByCpfCnpj.id
            }

            if (!id) return redirect(action: "index")

            Payer payer = payerService.show(id)

            return [payer: payer]
        } catch (Exception e) {
            redirect(action: "index")
        }
    }

    def deleteOrRestore() {
        try {
            Long id = params.id as Long

            if (!id) {
                def payerByEmail = Payer.findByEmail(params.email as String)
                if (payerByEmail) id = payerByEmail.id
            }

            if (!id) {
                def payerByCpfCnpj = Payer.findByCpfCnpj(StringUtils.removeNonNumeric(params.cpfCnpj as String))
                if (payerByCpfCnpj) id = payerByCpfCnpj.id
            }

            if (!id) return redirect(action: "restore")

            payerService.deleteOrRestore(id)
            redirect(action: "show", id: id)
        } catch (Exception e) {
            redirect(action: "index")
        }
    }
}
