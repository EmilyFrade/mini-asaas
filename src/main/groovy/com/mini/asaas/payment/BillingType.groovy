package com.mini.asaas.payment

import com.mini.asaas.utils.MessageSourceUtils

enum BillingType {
    BANKSLIP,
    CREDIT_CARD,
    DEBIT_CARD,
    PIX

    public String getLabel() {
        return MessageSourceUtils.getEnumLabel(this)
    }

    public static BillingType parseFromString(String type) {
        return valueOf(type.toUpperCase())
    }

    public Boolean isValid() {
        return [BANKSLIP,
                CREDIT_CARD,
                DEBIT_CARD,
                PIX].contains(this)
    }
}