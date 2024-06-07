package com.mini.asaas.payment

import com.mini.asaas.utils.MessageSourceUtils

enum PaymentStatus {

    CANCELED("secondary"),
    OVERDUE("danger"),
    PENDING("warning"),
    RECEIVED("success")

    String theme

    PaymentStatus(String theme) {
        this.theme = theme
    }

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
}