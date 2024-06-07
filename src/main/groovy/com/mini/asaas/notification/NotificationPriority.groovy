package com.mini.asaas.notification

enum NotificationPriority {
    LOW(0), MEDIUM(1), HIGH(2)

    private final Integer value

    public NotificationPriority(Integer value) {
        this.value = value
    }

    public Integer getValue() {
        return this.value
    }

    public static NotificationPriority parseFromString(String priority) {
        try {
            return valueOf(priority.toUpperCase())
        } catch (Exception exception) {
            return null
        }
    }

    public static NotificationPriority parseFromValue(Integer value) {
        for (NotificationPriority priority : values()) {
            if (priority.getValue() == value) return priority
        }

        return null
    }
}