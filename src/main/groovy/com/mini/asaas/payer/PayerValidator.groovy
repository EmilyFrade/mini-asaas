package com.mini.asaas.payer

import com.mini.asaas.Payer
import com.mini.asaas.base.BaseValidator
import com.mini.asaas.utils.CpfCnpjUtils
import com.mini.asaas.utils.ZipCodeUtils
import com.mini.asaas.validation.BusinessValidation

class PayerValidator extends BaseValidator {

    private static final PHONE_NUMBER_REGEX = /^(\+55)?\s?(\(?\d{2}\)?)?\s?\d{4,5}-?\d{4}$/
    private static final EMAIL_REGEX = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/

    public BusinessValidation validateCpfCnpj(String cpfCnpj) {
        if (!CpfCnpjUtils.isValidCpfCnpj(cpfCnpj)) {
            validationResult.addError("invalid.cpfCnpj")
        }
        if (Payer.countByCpfCnpj(cpfCnpj) > 0) {
            validationResult.addError("alreadyExists.cpfCnpj")
        }

        return validationResult
    }

    public BusinessValidation validateEmail(String email) {
        if (!email.matches(EMAIL_REGEX)) {
            validationResult.addError("invalid.email")
        }
        if (Payer.countByEmail(email) > 0) {
            validationResult.addError("alreadyExists.email")
        }

        return validationResult
    }

    public BusinessValidation validatePhoneNumber(String phoneNumber) {
        if(!phoneNumber.matches(PHONE_NUMBER_REGEX)) {
            validationResult.addError("invalid.phoneNumber")
        }

        return validationResult
    }

    public BusinessValidation validateZipCode(String zipCode) {
        if (!ZipCodeUtils.isValid(zipCode, false)) {
            validationResult.addError("invalid.zipCode")
        }

        return validationResult
    }

    public BusinessValidation validateBirthDate(Date birthDate) {
        if (birthDate > new Date()) {
            validationResult.addError("future.birthDate")
        }

        return validationResult
    }
}
