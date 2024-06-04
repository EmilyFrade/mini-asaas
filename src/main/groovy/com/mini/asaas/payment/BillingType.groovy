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
        try {
            return valueOf(type.toUpperCase())
        } catch (Exception e) {
            return null
        }
    }
}