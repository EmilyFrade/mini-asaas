package com.mini.asaas.email

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
interface SendEmailService {
    void send(Long emailMessageId)
}