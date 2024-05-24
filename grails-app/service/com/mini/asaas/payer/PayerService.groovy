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

    PayerRepository payerRepository

    public Payer save(PayerAdapter adapter) {
        Payer payer = new Payer()

        payer = validate(adapter, payer)
        if (payer.hasErrors()) throw new BusinessException(DomainErrorUtils.getFirstValidationMessage(payer))

        payer = buildPayer(adapter, payer)

        if(!payer.save()) throw new BusinessException(DomainErrorUtils.getFirstValidationMessage(payer))

        return payer
    }

    public Payer update(PayerAdapter adapter, Long id) {
        Payer payer = payerRepository.findById(id)

        if (!payer) {
            throw new RuntimeException("Pagador não encontrado")
        }

        payer = validate(adapter, payer)
        if (payer.hasErrors()) throw new BusinessException(DomainErrorUtils.getFirstValidationMessage(payer))

        payer = buildPayer(adapter, payer)
        payer.markDirty()

        if(!payer.save()) throw new BusinessException(DomainErrorUtils.getFirstValidationMessage(payer))

        return payer
    }

    private Payer validate(PayerAdapter adapter, Payer payer) {
        PayerValidator validator = new PayerValidator()

        if (adapter.email != payer.email) validator.validateEmail(adapter.email)
        if (adapter.cpfCnpj != payer.cpfCnpj) validator.validateCpfCnpj(adapter.cpfCnpj)
        validator.validatePhoneNumber(adapter.phoneNumber)
        validator.validateBirthDate(adapter.birthDate)
        validator.validateZipCode(adapter.zipCode)

        BusinessValidation validationResult = validator.validationResult

        if (!validationResult.isValid()) {
            DomainErrorUtils.addError(payer, validationResult.getFirstErrorCode(), validationResult.getFirstErrorMessage())
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