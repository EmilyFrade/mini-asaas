package com.mini.asaas.email

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class EmailAdapter {

    String to

    String subject

    String body

    Boolean isHTML

    Date sentDate

    public EmailAdapter(Map params) {
        this.to = params.to
        this.subject = params.subject
        this.body = params.body
        this.isHTML = params.isHTML as Boolean
        this.sentDate = params.sentDate as Date
    }
}
