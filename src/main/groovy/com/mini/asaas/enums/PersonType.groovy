package com.mini.asaas.enums

import com.mini.asaas.utils.CpfCnpjUtils
import grails.compiler.GrailsCompileStatic
import grails.util.Holders

enum PersonType {
    NATURAL,
    LEGAL

    public String getLabel() {
        String messageCode = "personType.${this.name()}.label"
        return Holders.applicationContext
                .getBean('messageSource')
                .getMessage(messageCode, null, "", Locale.getDefault())
    }

    public static PersonType fromString(String value) {
        try {
            return valueOf(value.toUpperCase())
        } catch (Exception ignored) {
            throw new IllegalArgumentException("'${value}' não é um tipo de pessoa válido.")
        }
    }

    public static PersonType fromCpfCnpj(String cpfCnpj) {
        if (CpfCnpjUtils.isValidCPF(cpfCnpj)) return NATURAL
        if (CpfCnpjUtils.isValidCNPJ(cpfCnpj)) return LEGAL
        throw new IllegalArgumentException("O valor não é um CPF/CNPJ válido")
    }
}