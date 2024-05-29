package com.mini.asaas.customer

import com.mini.asaas.utils.MessageSourceUtils

enum CompanyType {
    ASSOCIATION,
    INDIVIDUAL,
    LIMITED,
    MEI,
    NON_PROFIT_ASSOCIATION

    public String getLabel() {
        return MessageSourceUtils.getEnumLabel(this)
    }

    public static CompanyType parseFromString(String companyType) {
        try {
            valueOf(companyType.toUpperCase())
        } catch (Exception e) {
            return null
        }
    }

}