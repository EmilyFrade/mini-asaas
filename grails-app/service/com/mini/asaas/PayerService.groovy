package com.mini.asaas

import core.dtos.PayerDTO
import grails.compiler.GrailsCompileStatic
import grails.gorm.transactions.Transactional

@GrailsCompileStatic
@Transactional
class PayerService {

    public Payer save(PayerDTO dto) {
        Payer payer = new Payer()

        payer.name = dto.name
        payer.email = dto.email
        payer.cpfCnpj = dto.cpfCnpj
        payer.phoneNumber = dto.phoneNumber
        payer.personType = dto.personType
        payer.address = dto.address
        return payer.save(failOnError: true)
    }

    public Payer update(PayerDTO dto, Map params) {
        Payer payer = Payer.get(params.id as Long)

        if (!payer) {
            throw new RuntimeException("Pagador não encontrado")
        }

        payer.name = dto.name
        payer.email = dto.email
        payer.cpfCnpj = dto.cpfCnpj
        payer.phoneNumber = dto.phoneNumber
        payer.personType = dto.personType
        payer.address = dto.address
        payer.markDirty()

        return payer.save(failOnError: true)
    }

    public List<Payer> listAll() {
        return Payer.list()
    }
}
