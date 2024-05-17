package com.mini.asaas.modules.person.enums

import com.mini.asaas.shared.utils.CpfCnpjUtils
import grails.compiler.GrailsCompileStatic
import grails.util.Holders

@GrailsCompileStatic
enum PersonType {
    NATURAL,
    LEGAL

    public String getLabel() {
        Locale locale = new Locale("pt", "BR")
        String messageCode = "personType.${this.name()}.label"
        return Holders.applicationContext
                .getBean("messageSource")
                .getMessage(messageCode, null, "", Locale.getDefault())
    }

    public static PersonType fromString(String value) {
        try {
            return valueOf(value.toUpperCase())
        } catch (Exception ignored) {
            throw new RuntimeException("'${value}' não é um tipo de pessoa válido.")
        }
    }

    public static PersonType fromCpfCnpj(String cpfCnpj) {
        if (CpfCnpjUtils.isValidCPF(cpfCnpj)) return NATURAL
        if (CpfCnpjUtils.isValidCNPJ(cpfCnpj)) return LEGAL
        throw new IllegalArgumentException("O valor não é um CPF/CNPJ válido")
    }
}