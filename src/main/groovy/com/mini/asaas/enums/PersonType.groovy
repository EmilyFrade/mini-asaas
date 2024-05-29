package com.mini.asaas.enums

import com.mini.asaas.utils.CpfCnpjUtils
import com.mini.asaas.utils.MessageSourceUtils

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
        return MessageSourceUtils.getEnumLabel(this)
    }

    public static PersonType fromString(String value) {
        try {
            return valueOf(value.toUpperCase())
        } catch (Exception ignored) {
            return null
        }
    }

    public static PersonType parseFromCpfCnpj(String cpfCnpj) {
        if (CpfCnpjUtils.isCPF(cpfCnpj)) return NATURAL
        if (CpfCnpjUtils.isCNPJ(cpfCnpj)) return LEGAL
        return null
    }
}