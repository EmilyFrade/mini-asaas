package com.mini.asaas.customer

import com.mini.asaas.base.BaseValidator
import com.mini.asaas.utils.CpfCnpjUtils
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
        if (cpfCnpj && !CpfCnpjUtils.isValidCpfCnpj(cpfCnpj)) {
            validationResult.addError("invalid.cpfCnpj")
        }
        return this
    }

    public CustomerValidator validateEmail(String email) {
        if (email && !EmailUtils.isValid(email)) {
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
        if (zipCode && !ZipCodeUtils.isValid(zipCode, false)) {
            validationResult.addError("invalid.zipCode")
        }
        return this
    }

    public CustomerValidator validatePhoneNumber(String phoneNumber) {
        if (phoneNumber && !phoneNumber.matches(PHONE_NUMBER_REGEX)) {
            validationResult.addError("invalid.phoneNumber")
        }
        return this
    }

    public CustomerValidator validateBirthDate(Date birthDate) {
        if (birthDate) {
            if(birthDate > new Date()){
                validationResult.addError("future.birthDate")
            }
            Calendar birthCalendar = Calendar.getInstance()
            birthCalendar.setTime(birthDate)

            Calendar today = Calendar.getInstance()

            Integer age = today.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR)

            Boolean todayMonthIsBeforeBirthMonth = today.get(Calendar.MONTH) < birthCalendar.get(Calendar.MONTH)
            Boolean todayMonthIsEqualBirthMonth = today.get(Calendar.MONTH) == birthCalendar.get(Calendar.MONTH)
            Boolean todayDayIsBeforeBirthDay = today.get(Calendar.DAY_OF_MONTH) < birthCalendar.get(Calendar.DAY_OF_MONTH)

            if (todayMonthIsBeforeBirthMonth || (todayMonthIsEqualBirthMonth && todayDayIsBeforeBirthDay)) age--

            if (age < 18) validationResult.addError("underage")
        }
        return this
    }

}
