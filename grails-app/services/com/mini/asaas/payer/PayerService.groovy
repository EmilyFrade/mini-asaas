package com.mini.asaas.payer

import com.mini.asaas.customer.Customer
import com.mini.asaas.customer.CustomerService
import com.mini.asaas.exceptions.BusinessException
import com.mini.asaas.repository.PayerRepository
import com.mini.asaas.utils.DomainErrorUtils
import com.mini.asaas.validation.BusinessValidation
import grails.compiler.GrailsCompileStatic
import grails.gorm.transactions.Transactional

@GrailsCompileStatic
@Transactional
class PayerService {

    CustomerService customerService

    BusinessValidation validationResult

    public Payer save(PayerAdapter adapter) {
        Payer payer = new Payer()
        Customer customer = customerService.show()

        if (!customer) throw new RuntimeException("Cliente não encontrado")

        payer = validate(adapter, payer, customer)

        if (payer.hasErrors()) throw new BusinessException(DomainErrorUtils.getFirstValidationMessage(payer), validationResult.getFirstErrorCode())

        payer = buildPayer(adapter, payer)

        payer.customer = customer
        payer.save(failOnError: true)

        return payer
    }

    public Payer update(PayerAdapter adapter, Long id) {
        Payer payer = PayerRepository.findById(id, false)

        if (!payer) throw new RuntimeException("Pagador não encontrado")


        payer = validate(adapter, payer, payer.customer)

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

    public List<Payer> listAllNotDeleted() {
        Customer customer = customerService.show()
        return PayerRepository.listAllByCustomerAndNotDeleted(customer)
    }

    public List<Payer> listAllDeleted() {
        Customer customer = customerService.show()
        return PayerRepository.listAllByCustomerAndDeleted(customer)
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