package com.mini.asaas.enums

enum AlertType {
    INFO,
    SUCCESS,
    WARNING,
    ERROR

    public String getValue() {
        return this.name().toLowerCase();
    }
}