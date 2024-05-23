package com.mini.asaas.utils

import spock.lang.Specification

class StringUtilsTest extends Specification {

    void "it should remove non-numeric characters"() {
        expect:
        StringUtils.removeNonNumeric(input) == expected

        where:
        input                 | expected
        "123.456.789-01"      | "12345678901"
        "12345678901"         | "12345678901"
        "12.345.678/9012-34"  | "12345678901234"
        "123456789012345"     | "123456789012345"
        "invalid"             | ""
        null                  | null
    }

    void "it should convert pascal case to camel case"() {
        expect:
        StringUtils.pascalToCamelCase(input) == expected

        where:
        input                 | expected
        "PascalCase"          | "pascalCase"
        "PascalCaseTest"      | "pascalCaseTest"
        "Pascal"              | "pascal"
        "camelCase"           | "camelCase"
        "Kebab-case"          | "kebab-case"
        "Snake_case"          | "snake_case"
        ""                    | ""
        null                  | null
    }

    void "it should trim a value if it is a string"() {
        expect:
        StringUtils.ensureStringAndTrim(input) == expected

        where:
        input                 | expected
        "  test  "            | "test"
        "  test"              | "test"
        "test  "              | "test"
        "test"                | "test"
        "  "                  | null
        ""                    | null
        null                  | null
        123                   | null
        []                    | null
    }

}
