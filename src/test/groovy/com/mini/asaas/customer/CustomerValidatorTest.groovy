package com.mini.asaas.customer

import spock.lang.Specification

import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

class CustomerValidatorTest extends Specification {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy")

    private static Date getDate(String date) {
        if (!date) return null
        return DATE_FORMAT.parse(date)
    }

    void "it should validate required fields"() {
        given:
            CustomerValidator validator = new CustomerValidator()

        expect:
            validator.validateRequiredFields(requiredFields).validationResult.isValid() == expected

        where:
            requiredFields                                              | expected
            [name: "John Doe", type: "individual", status: null]        | false
            [name: "John Doe", type: "", status: "active"]              | false
            [name: "John Doe", type: "individual", status: "active"]    | true
    }

    void "it should validate CPF/CNPJ"() {
        given:
            CustomerValidator validator = new CustomerValidator()

        expect:
            validator.validateCpfCnpj(cpfCnpj).validationResult.isValid() == expected

        where:
            cpfCnpj               | expected
            null                  | false
            ""                    | false
            "11.111.111/1111-11"  | false
            "11.444.777/0001-61"  | true
            "invalid"             | false
            "786.334.840-48"      | true
            "111.111.111-11"      | false
    }

    void "it should validate email"() {
        given:
            CustomerValidator validator = new CustomerValidator()

        expect:
            validator.validateEmail(email).validationResult.isValid() == expected

        where:
            email                 | expected
            null                  | false
            ""                    | false
            "  "                  | false
            "johndoe@mail.com.br" | true
            "invalid"             | false
    }

    void "it should validate zip code"() {
        given:
            CustomerValidator validator = new CustomerValidator()

        expect:
            validator.validateZipCode(zipCode).validationResult.isValid() == expected

        where:
            zipCode               | expected
            null                  | false
            ""                    | false
            "  "                  | false
            "44002202"            | true
            "44003-203"           | false
    }

    void "it should validate phone number"() {
        given:
            CustomerValidator validator = new CustomerValidator()

        expect:
            validator.validatePhoneNumber(phoneNumber).validationResult.isValid() == expected

        where:
            phoneNumber           | expected
            null                  | false
            ""                    | false
            "  "                  | false
            "71999999999"         | true
            "7199999999"          | true
            "719999999999"        | false
    }

    void "it should return true when the birth date is valid"() {
        given:
            CustomerValidator validator = new CustomerValidator()

        expect:
            validator.validateBirthDate(getDate("01/01/2000")).validationResult.isValid() == true
    }

    void "it should return false when the birth date is in the future"() {
        given:
            Date currentDate = new Date()
            Date futureDate = new Date(currentDate.time + TimeUnit.DAYS.toMillis(1))
            CustomerValidator validator = new CustomerValidator()

        expect:
            validator.validateBirthDate(futureDate).validationResult.isValid() == false
    }

    void "it should return false when the birth date is null"() {
        given:
            CustomerValidator validator = new CustomerValidator()

        expect:
            validator.validateBirthDate(null).validationResult.isValid() == false
    }

    void "it should return false when the birth date is less than 18 years"() {
        given:
            Date currentDate = new Date()
            Date almostEighteenYearsAgo = new Date(currentDate.time - TimeUnit.DAYS.toMillis(365 * 18 - 1))
            CustomerValidator validator = new CustomerValidator()

        expect:
            validator.validateBirthDate(almostEighteenYearsAgo).validationResult.isValid() == false
    }

}
