package com.mini.asaas.customer

import com.mini.asaas.base.BaseValidator
import com.mini.asaas.utils.CpfCnpjUtils
import com.mini.asaas.utils.DateUtils
import com.mini.asaas.utils.EmailUtils
import com.mini.asaas.utils.ZipCodeUtils

class CustomerValidator extends BaseValidator {

    private static final PHONE_NUMBER_REGEX = /(\d{10,11})/

    public CustomerValidator validateRequiredFields(Map requiredFields) {
        Boolean failed = requiredFields.any { !it.value }
        if (failed) validationResult.addError("notFilled.requiredFields")
        return this
    }

    public CustomerValidator validateCpfCnpj(String cpfCnpj) {
        if (!CpfCnpjUtils.isValidCpfCnpj(cpfCnpj)) {
            validationResult.addError("invalid.cpfCnpj")
        }
        return this
    }

    public CustomerValidator validateEmail(String email) {
        if (!EmailUtils.isValid(email)) {
            validationResult.addError("invalid.email")
        }
        return this
    }

    public CustomerValidator validateIfCpfCnpjExists(String cpfCnpj) {
        if (cpfCnpj && Customer.findByCpfCnpj(cpfCnpj)) {
            validationResult.addError("alreadyExists.cpfCnpj")
        }
        return this
    }

    public CustomerValidator validateIfEmailExists(String email) {
        if (email && Customer.findByEmail(email)) {
            validationResult.addError("alreadyExists.email")
        }
        return this
    }

    public CustomerValidator validateZipCode(String zipCode) {
        if (!ZipCodeUtils.isValid(zipCode, false)) {
            validationResult.addError("invalid.zipCode")
        }
        return this
    }

    public CustomerValidator validatePhoneNumber(String phoneNumber) {
        if (!phoneNumber || !phoneNumber.matches(PHONE_NUMBER_REGEX)) {
            validationResult.addError("invalid.phoneNumber")
        }
        return this
    }

    public CustomerValidator validateBirthDate(Date birthDate) {
        if (birthDate) {
            if(birthDate > new Date()) validationResult.addError("future.birthDate")
            Integer age = DateUtils.getDifferenceInYears(birthDate, new Date())
            if (age < 18) validationResult.addError("underage")
        } else {
            validationResult.addError("notFilled.birthDate")
        }
        return this
    }

}
