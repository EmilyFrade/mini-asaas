package com.mini.asaas.payment

import com.mini.asaas.utils.MessageSourceUtils

enum PaymentStatus {
    CANCELED,
    OVERDUE,
    PENDING,
    RECEIVED,
    RECEIVED_IN_CASH

    public String getLabel() {
        return MessageSourceUtils.getEnumLabel(this)
    }

    public static PaymentStatus parseFromString(String status) {
        try {
            return valueOf(status.toUpperCase())
        } catch (Exception e) {
            return null
        }
    }
}