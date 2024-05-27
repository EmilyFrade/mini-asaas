package com.mini.asaas.utils

import spock.lang.Specification

class EmailUtilsTest extends Specification {

    void "it should validate a email"() {
        expect:
        EmailUtils.isValid(email) == expected

        where:
        email                 | expected
        "johndoe@mail.com"    | true
        "johndoe@mail.com.br" | true
        "invalid"             | false
        "johndoe@mail"        | true
        "johndoe@mail."       | true
        "john.doe@"           | false
        "@mail.com"           | false
    }

    void "it should get the domain from a email"() {
        expect:
        EmailUtils.getDomain(email) == expected

        where:
        email              | expected
        "johndoe@mail.com" | "mail.com"
        "john@mail.com.br" | "mail.com.br"
        "john@"            | null
        "@mail.com"        | null
        "john"             | null
        ""                 | null
        null               | null
        "john@mail"        | "mail"
    }

    void "it should get the local part from a email"() {
        expect:
        EmailUtils.getLocalPart(email) == expected

        where:
        email              | expected
        "johndoe@mail.com" | "johndoe"
        "j.ohn@m.com.br"   | "j.ohn"
        "john@"            | null
        "john"             | null
        ""                 | null
        null               | null
    }

}
