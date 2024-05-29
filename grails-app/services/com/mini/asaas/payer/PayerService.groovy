package com.mini.asaas.payer

import com.mini.asaas.exceptions.BusinessException
import com.mini.asaas.repository.PayerRepository
import com.mini.asaas.utils.DomainErrorUtils
import com.mini.asaas.validation.BusinessValidation
import grails.compiler.GrailsCompileStatic
import grails.gorm.transactions.Transactional

@GrailsCompileStatic
@Transactional
class PayerService {

    BusinessValidation validationResult

    public Payer save(PayerAdapter adapter) {
        Payer payer = new Payer()

        payer = validate(adapter, payer)
        if (payer.hasErrors()) throw new BusinessException(DomainErrorUtils.getFirstValidationMessage(payer), validationResult.getFirstErrorCode())

        payer = buildPayer(adapter, payer)

        return payer.save(failOnError: true)
    }

    public Payer update(PayerAdapter adapter, Long id) {
        Payer payer = PayerRepository.findById(id, false)

        if (!payer) throw new RuntimeException("Pagador não encontrado")

        payer = validate(adapter, payer)
        if (payer.hasErrors()) throw new BusinessException(DomainErrorUtils.getFirstValidationMessage(payer), validationResult.getFirstErrorCode())

        payer = buildPayer(adapter, payer)
        payer.markDirty()

        return payer.save(failOnError: true)
    }

    public Payer show(Long id) {
        Payer payer = PayerRepository.findById(id, false)
        if (!payer) throw new RuntimeException("Pagador não encontrado")
        return payer
    }

    public void deleteOrRestore(Long id) {
        Payer payer = PayerRepository.findById(id)

        if (!payer) throw new RuntimeException("Pagador não encontrado")

        payer.deleted = !payer.deleted
        payer.markDirty()
        payer.save(failOnError: true)
    }

    private Payer validate(PayerAdapter adapter, Payer payer) {
        PayerValidator validator = new PayerValidator()
        validator.validateAll(adapter, payer)

        validationResult = validator.validationResult

        if (!validationResult.isValid()) {
            DomainErrorUtils.addBusinessRuleErrors(payer, validationResult.errors)
        }

        return payer
    }

    private Payer buildPayer(PayerAdapter adapter, Payer payer) {
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
        payer.birthDate = adapter.birthDate

        return payer
    }
}