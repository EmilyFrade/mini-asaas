package com.mini.asaas.payment

import com.mini.asaas.enums.AlertType
import com.mini.asaas.exceptions.BusinessException
import com.mini.asaas.repository.CustomerRepository
import com.mini.asaas.repository.PayerRepository
import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_USER"])
class PaymentController {

    PaymentService paymentService

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
        } catch (BusinessException e) {
            flash.message = e.getMessage()
            flash.status = AlertType.ERROR.getValue()
            render view: "create"
        } catch (Exception e) {
            flash.message = "Ocorreu um erro durante a criação, aguarde um momento e tente novamente."
            flash.status = AlertType.ERROR.getValue()
            render view: "create"
        }
    }
}
