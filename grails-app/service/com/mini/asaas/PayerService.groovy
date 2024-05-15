package com.mini.asaas

import core.dtos.PayerDTO
import grails.compiler.GrailsCompileStatic
import grails.gorm.transactions.Transactional

@GrailsCompileStatic
@Transactional
class PayerService {
    public Payer save(PayerDTO dto) {
        Payer payer = dto.toPayer()
        return payer.save(failOnError: true)
    }
}
