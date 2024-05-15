package com.mini.asaas

import core.dtos.PayerDTO
import grails.compiler.GrailsCompileStatic
import grails.gorm.transactions.Transactional

import javax.persistence.EntityNotFoundException

@GrailsCompileStatic
@Transactional
class PayerService {
    public Payer save(PayerDTO dto) {
        Payer payer = dto.toPayer()
        return payer.save(failOnError: true)
    }

    public Payer update(PayerDTO dto, Map params) {
        Payer payer = Payer.get(params.id as Serializable)

        if (!payer) {
            throw new EntityNotFoundException("Pagador não encontrado")
        }

        payer = dto.updatePayer(payer)
        payer.markDirty()

        return payer.save(failOnError: true)
    }

    public Payer deleteRestore(Map params) {
        Payer payer = Payer.get(params.id as Serializable)

        if (!payer) {
            throw new EntityNotFoundException("Pagador não encontrado")
        }

        payer.deleted = !payer.deleted
        payer.markDirty()

        return payer.save(failOnError: true)
    }

    static List<Payer> listNotDeleted() {
        return Payer.findAllByDeleted(false)
    }

    static List<Payer> listDeleted() {
        return Payer.findAllByDeleted(true)
    }
}
