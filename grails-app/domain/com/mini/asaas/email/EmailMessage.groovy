package com.mini.asaas.email

import com.mini.asaas.base.BaseEntity

class EmailMessage extends BaseEntity {

    public static final Integer MAX_ATTEMPTS = 3

    String from

    String fromName

    String to

    String subject

    String body

    Integer attempts

    EmailStatus status

    Boolean isHTML

    Date sentDate

    static constraints = {
        from blank: false
        fromName blank: false
        to blank: false
        subject blank: false
        body nullable: true, blank: false
        attempts min: 0, max: MAX_ATTEMPTS
    }

    static mapping = {
        body type: "text"
        from column: "from_email"
        to column: "to_email"
    }
}
