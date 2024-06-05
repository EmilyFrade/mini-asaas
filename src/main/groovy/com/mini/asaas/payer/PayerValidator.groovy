package com.mini.asaas.payer

import com.mini.asaas.base.BaseValidator
import com.mini.asaas.customer.Customer
import com.mini.asaas.utils.CpfCnpjUtils
import com.mini.asaas.utils.EmailUtils
import com.mini.asaas.utils.ZipCodeUtils

class PayerValidator extends BaseValidator {

    private static final PHONE_NUMBER_REGEX = /^(\+55)?\s?(\(?\d{2}\)?)?\s?\d{4,5}-?\d{4}$/

    public PayerValidator validateAll(PayerAdapter adapter, Payer payer, Customer customer) {
        if (adapter.email != payer.email) this.validateEmail(adapter.email, customer)
        if (adapter.cpfCnpj != payer.cpfCnpj) this.validateCpfCnpj(adapter.cpfCnpj, customer)
        this.validatePhoneNumber(adapter.phoneNumber)
        this.validateBirthDate(adapter.birthDate)
        this.validateZipCode(adapter.zipCode)

        return this
    }

    public PayerValidator validateCpfCnpj(String cpfCnpj, Customer customer) {
        if (!CpfCnpjUtils.isValidCpfCnpj(cpfCnpj)) {
            validationResult.addError("invalid.cpfCnpj")
        }
        if (PayerRepository.query([cpfCnpj: cpfCnpj, customerId: customer.id, deletedOnly: true]).exists()) {
            validationResult.addError("alreadyExistsAndDeleted.cpfCnpj")
        } else if (PayerRepository.query([cpfCnpj: cpfCnpj, customerId: customer.id]).exists()) {
            validationResult.addError("alreadyExists.cpfCnpj")
        }

        return this
    }

    public PayerValidator validateEmail(String email, Customer customer) {
        if (!EmailUtils.isValid(email)) {
            validationResult.addError("invalid.email")
        }

        Payer payer = PayerRepository.query([email: email, customerId: customer.id, includeDeleted: true]).get()

        if (!payer) return this

        if (payer.deleted) {
            validationResult.addError("alreadyExistsAndDeleted.email")
        } else {
            validationResult.addError("alreadyExists.email")
        }
        
        return this
    }

    public PayerValidator validatePhoneNumber(String phoneNumber) {
        if (!phoneNumber.matches(PHONE_NUMBER_REGEX)) {
            validationResult.addError("invalid.phoneNumber")
        }

        return this
    }

    public PayerValidator validateZipCode(String zipCode) {
        if (!ZipCodeUtils.isValid(zipCode, false)) {
            validationResult.addError("invalid.zipCode")
        }

        return this
    }

    public PayerValidator validateBirthDate(Date birthDate) {
        if (birthDate > new Date()) {
            validationResult.addError("future.birthDate")
        }

        return this
    }
}
