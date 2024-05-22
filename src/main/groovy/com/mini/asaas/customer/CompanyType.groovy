package com.mini.asaas.customer

import com.mini.asaas.utils.MessageSourceUtils

enum CompanyType {
    ASSOCIATION,
    INDIVIDUAL,
    LIMITED,
    MEI,
    NON_PROFIT_ASSOCIATION

    public Boolean isAssociation() {
        return this == ASSOCIATION
    }

    public Boolean isIndividual() {
        return this == INDIVIDUAL
    }

    public Boolean isLimited() {
        return this == LIMITED
    }

    public Boolean isMei() {
        return this == MEI
    }

    public Boolean isNonProfitAssociation() {
        return this == NON_PROFIT_ASSOCIATION
    }

    public String getLabel() {
        return MessageSourceUtils.getEnumLabel(this)
    }

    public static List<CompanyType> getAvailableForCustomerToChoose() {
        return [ASSOCIATION, INDIVIDUAL, LIMITED, MEI]
    }

    public static CompanyType parseFromString(String companyType) {
        try {
            valueOf(companyType.toUpperCase())
        } catch (Exception e) {
            return null
        }
    }

}