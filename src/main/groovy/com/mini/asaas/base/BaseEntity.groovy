package com.mini.asaas.base

import java.time.LocalDateTime

abstract class BaseEntity {

    LocalDateTime dateCreated

    LocalDateTime lastUpdated

    Boolean deleted = false

    static mapping = {
        tablePerHierarchy false
    }
}
