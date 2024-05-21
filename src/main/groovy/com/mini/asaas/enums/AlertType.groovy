package com.mini.asaas.enums

enum AlertType {
    INFO("info"),
    SUCCESS("success"),
    WARNING("warning"),
    ERROR("error")

    final String value

    AlertType(String value) {
        this.value = value
    }

    public String getValue() {
        return value
    }
}