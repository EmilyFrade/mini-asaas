package com.mini.asaas

import core.entities.Person

class Payer extends Person {
    static namedQueries = {
        if (!Boolean.valueOf(search.includeDeleted)) {
            eq('deleted', false)
        }

        if (search.containsKey('id')) {
            eq('id', search.id)
        }
    }
}