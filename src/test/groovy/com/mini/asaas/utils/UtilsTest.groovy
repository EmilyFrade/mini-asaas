package com.mini.asaas.utils

import spock.lang.Specification

class UtilsTest extends Specification {

    void "it should return an empty map if params is null"() {
        expect:
        Utils.normalizeParams(null) == [:]
    }

    void "it should normalize and trim parameters correctly"() {
        expect:
        Utils.normalizeParams(input) == expected

        where:
        input                                                    | expected
        ["key1": "  value1  ", "key2": "value2"]                 | ["key1": "value1", "key2": "value2"]
        ["key1": "  value1", "key2": "value2  "]                 | ["key1": "value1", "key2": "value2"]
        ["key1": "value1", "key2": "value2"]                     | ["key1": "value1", "key2": "value2"]
        ["key1": "  ", "key2": "   "]                            | ["key1": null, "key2": null]
        ["key1": null, "key2": null]                             | ["key1": null, "key2": null]
        ["key1": "value1", "key2": 123, "key3": [1, 2, 3]]       | ["key1": "value1", "key2": null, "key3": null]
    }
}
