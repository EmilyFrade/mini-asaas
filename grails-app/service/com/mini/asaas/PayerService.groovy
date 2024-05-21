package com.mini.asaas

import com.mini.asaas.adapters.PayerAdapter
import grails.compiler.GrailsCompileStatic
import grails.gorm.transactions.Transactional

@GrailsCompileStatic
@Transactional
class PayerService {

    public Payer save(Map params) {
        PayerAdapter adapter = new PayerAdapter(params)
        Payer payer = new Payer()

        payer = fromAdapterToPayer(payer, adapter)

        return payer.save(failOnError: true)
    }

    public Payer update(Map params) {
        Payer payer = Payer.get(params.id as Long)
        PayerAdapter adapter = new PayerAdapter(params)

        if (!payer) {
            throw new RuntimeException("Pagador não encontrado")
        }

        payer = fromAdapterToPayer(payer, adapter)
        payer.markDirty()

        return payer.save(failOnError: true)
    }

    public List<Payer> listAll() {
        return Payer.list()
    }

    private Payer fromAdapterToPayer(Payer payer, PayerAdapter adapter) {
        payer.name = adapter.name
        payer.email = adapter.email
        payer.cpfCnpj = adapter.cpfCnpj
        payer.phoneNumber = adapter.phoneNumber
        payer.personType = adapter.personType
        payer.address = adapter.address
        payer.addressNumber = adapter.addressNumber
        payer.complement = adapter.complement
        payer.province = adapter.province
        payer.city = adapter.city
        payer.state = adapter.state
        payer.zipCode = adapter.zipCode

        return payer
    }
}
