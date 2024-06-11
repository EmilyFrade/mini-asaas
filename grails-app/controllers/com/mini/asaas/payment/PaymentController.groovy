package com.mini.asaas.payment

import com.mini.asaas.customer.CustomerRepository
import com.mini.asaas.enums.AlertType
import com.mini.asaas.exceptions.BusinessException
import com.mini.asaas.payer.PayerRepository
import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_SELLER"])
class PaymentController {

    PaymentService paymentService

    def create() {
        List<Customer> customerList = CustomerRepository.query().list()
        List<Payer> payerList = PayerRepository.query().list()

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
}
