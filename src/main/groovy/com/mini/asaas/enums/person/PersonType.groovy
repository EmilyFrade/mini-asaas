package com.mini.asaas.enums.person

import grails.compiler.GrailsCompileStatic
import grails.util.Holders

@GrailsCompileStatic
enum PersonType {
    NATURAL,
    LEGAL

    public String getLabel() {
        String messageCode = "personType.${this.name()}.label"
        return Holders.applicationContext
                .getBean("messageSource")
                .getMessage(messageCode, null, "", Locale.getDefault())
    }

    public static PersonType fromString(String value) {
        try {
            return valueOf(value.toUpperCase())
        } catch (Exception exception) {
            throw new RuntimeException("'${value}' não é um tipo de pessoa válido.")
        }
    }
}