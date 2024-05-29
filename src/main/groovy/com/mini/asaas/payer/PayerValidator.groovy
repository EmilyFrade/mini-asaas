package com.mini.asaas.payer

import com.mini.asaas.base.BaseValidator
import com.mini.asaas.utils.CpfCnpjUtils
import com.mini.asaas.utils.ZipCodeUtils

class PayerValidator extends BaseValidator {

    private static final PHONE_NUMBER_REGEX = /^(\+55)?\s?(\(?\d{2}\)?)?\s?\d{4,5}-?\d{4}$/
    private static final EMAIL_REGEX = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/

    public PayerValidator validateAll(PayerAdapter adapter, Payer payer) {
        if (adapter.email != payer.email) this.validateEmail(adapter.email)
        if (adapter.cpfCnpj != payer.cpfCnpj) this.validateCpfCnpj(adapter.cpfCnpj)
        this.validatePhoneNumber(adapter.phoneNumber)
        this.validateBirthDate(adapter.birthDate)
        this.validateZipCode(adapter.zipCode)

        return this
    }

    public PayerValidator validateCpfCnpj(String cpfCnpj) {
        if (!CpfCnpjUtils.isValidCpfCnpj(cpfCnpj)) {
            validationResult.addError("invalid.cpfCnpj")
        }
        if (Payer.countByCpfCnpj(cpfCnpj) > 0) {
            validationResult.addError("alreadyExists.cpfCnpj")
        }

        return this
    }

    public PayerValidator validateEmail(String email) {
        if (!email.matches(EMAIL_REGEX)) {
            validationResult.addError("invalid.email")
        }
        if (Payer.countByEmail(email) > 0) {
            validationResult.addError("alreadyExists.email")
        }

        return this
    }

    public PayerValidator validatePhoneNumber(String phoneNumber) {
        if(!phoneNumber.matches(PHONE_NUMBER_REGEX)) {
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
