package com.mini.asaas.shared.enums

import grails.compiler.GrailsCompileStatic
import grails.util.Holders

@GrailsCompileStatic
enum PersonType {
    NATURAL,
    LEGAL

    String getLabel() {
        Locale locale = new Locale("pt", "BR")
        String messageCode = "personType.${this.name()}.label"
        return Holders
                .applicationContext
                .getBean('messageSource')
                .getMessage(messageCode, null, "", locale)
    }

    static PersonType fromString(String value) {
        try {
            return valueOf(value.toUpperCase())
        } catch (Exception ignored) {
            throw new IllegalArgumentException("'${value}' não é um tipo de pessoa válido.")
        }
    }
}