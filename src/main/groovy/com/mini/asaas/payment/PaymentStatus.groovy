package com.mini.asaas.payment

import com.mini.asaas.utils.MessageSourceUtils

enum PaymentStatus {
    CANCELED,
    OVERDUE,
    PENDING,
    RECEIVED

    public String getLabel() {
        return MessageSourceUtils.getEnumLabel(this)
    }

    public static PaymentStatus convert(status) {
        try {
            return valueOf(status.toUpperCase())
        } catch (Exception ignored) {
            return null
        }
    }

    public static List<PaymentStatus> canBeDeleted() {
        return [OVERDUE, PENDING]
    }
}