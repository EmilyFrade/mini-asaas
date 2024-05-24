package com.mini.asaas.payer

import com.mini.asaas.exceptions.BusinessException
import com.mini.asaas.utils.DomainErrorUtils
import com.mini.asaas.validation.BusinessValidation
import grails.compiler.GrailsCompileStatic
import grails.gorm.transactions.Transactional

@GrailsCompileStatic
@Transactional
class PayerService {

    public Payer save(Map params) {
        PayerAdapter adapter = new PayerAdapter(params)
        Payer payer = new Payer()

        payer = validateBeforeSave(adapter, payer)
        if (payer.hasErrors()) throw new BusinessException(DomainErrorUtils.getFirstValidationMessage(payer))

        payer = populatePayer(adapter, payer)
        payer.validate()
        if (payer.hasErrors()) throw new BusinessException(DomainErrorUtils.getFirstValidationMessage(payer))

        return payer.save(failOnError: true)
    }

    public Payer update(Map params) {
        Payer payer = Payer.get(params.id as Long)

        if (!payer) {
            throw new RuntimeException("Pagador não encontrado")
        }

        PayerAdapter adapter = new PayerAdapter(params)

        payer = validateBeforeSave(adapter, payer)
        if (payer.hasErrors()) throw new BusinessException(DomainErrorUtils.getFirstValidationMessage(payer))

        payer = populatePayer(adapter, payer)
        payer.validate()
        if (payer.hasErrors()) throw new BusinessException(DomainErrorUtils.getFirstValidationMessage(payer))

        payer.markDirty()

        return payer.save(failOnError: true)
    }

    public List<Payer> listAll() {
        return Payer.list()
    }

    private Payer validateBeforeSave(PayerAdapter adapter, Payer payer) {
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

    private Payer populatePayer(PayerAdapter adapter, Payer payer) {
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