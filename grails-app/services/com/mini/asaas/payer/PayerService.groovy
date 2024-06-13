package com.mini.asaas.payer

import com.mini.asaas.customer.Customer
import com.mini.asaas.exceptions.BusinessException
import com.mini.asaas.user.User
import com.mini.asaas.utils.DomainErrorUtils
import com.mini.asaas.validation.BusinessValidation
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService

@Transactional
class PayerService {

    BusinessValidation validationResult

    SpringSecurityService springSecurityService

    public Payer save(PayerAdapter adapter) {
        Payer payer = new Payer()
        Customer customer = (springSecurityService.loadCurrentUser() as User).customer
        if (!customer) throw new BusinessException("Cliente não encontrado")

        payer = validate(adapter, payer, customer)

        if (payer.hasErrors()) throw new BusinessException(DomainErrorUtils.getFirstValidationMessage(payer), validationResult.getFirstErrorCode())

        payer = buildPayer(adapter, payer)

        payer.customer = customer
        payer.save(failOnError: true)

        return payer
    }

    public Payer update(PayerAdapter adapter, Long id) {
        Long customerId = (springSecurityService.loadCurrentUser() as User).customerId
        Payer payer = PayerRepository.query([id: id, customerId: customerId]).get()
        if (!payer) throw new RuntimeException("Pagador não encontrado")

        payer = validate(adapter, payer, payer.customer)

        if (payer.hasErrors()) throw new BusinessException(DomainErrorUtils.getFirstValidationMessage(payer), validationResult.getFirstErrorCode())

        payer = buildPayer(adapter, payer)

        return payer.save(failOnError: true)
    }

    public Payer show(Long id) {
        Long customerId = (springSecurityService.loadCurrentUser() as User).customerId
        Payer payer = PayerRepository.query([id: id, customerId: customerId]).get()
        if (!payer) throw new RuntimeException("Pagador não encontrado")
        return payer
    }

    public void deleteOrRestore(Long id) {

        Long customerId = (springSecurityService.loadCurrentUser() as User).customerId
        Payer payer = PayerRepository.query([includeDeleted: true, id: id, customerId: customerId]).get()
        if (!payer) throw new RuntimeException("Pagador não encontrado")

        payer.deleted = !payer.deleted
        payer.markDirty()
        payer.save(failOnError: true)
    }

    public List<Payer> list() {
        Long customerId = (springSecurityService.loadCurrentUser() as User).customerId
        return PayerRepository.query([customerId: customerId]).list()
    }

    public List<Payer> listForRestoration() {
        Long customerId = (springSecurityService.loadCurrentUser() as User).customerId
        return PayerRepository.query([customerId: customerId, deletedOnly: true]).list()
    }

    private Payer validate(PayerAdapter adapter, Payer payer, Customer customer) {
        PayerValidator validator = new PayerValidator()
        validator.validateAll(adapter, payer, customer)

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