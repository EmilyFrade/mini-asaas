package com.mini.asaas.enums

import com.mini.asaas.utils.CpfCnpjUtils
import grails.util.Holders

enum PersonType {
    NATURAL,
    LEGAL

    public Boolean isNatural() {
        return this == NATURAL
    }

    public Boolean isLegal() {
        return this == LEGAL
    }

    public String getLabel() {
        String code = "personType.${this.name()}.label"
        Locale locale = Locale.getDefault()
        Object bean = Holders.applicationContext.getBean("messageSource")
        return bean.getMessage(code, null, "", locale)
    }

    public static PersonType fromString(String value) {
        try {
            return valueOf(value.toUpperCase())
        } catch (Exception ignored) {
            throw new IllegalArgumentException("'${value}' não é um tipo de pessoa válido.")
        }
    }

    public static PersonType parseFromCpfCnpj(String cpfCnpj) {
        if (CpfCnpjUtils.isCPF(cpfCnpj)) return NATURAL
        if (CpfCnpjUtils.isCNPJ(cpfCnpj)) return LEGAL
        throw new IllegalArgumentException("O valor informado não é um CPF nem um CNPJ.")
    }
}