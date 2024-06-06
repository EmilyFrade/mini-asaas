package com.mini.asaas.email

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
interface EmailService {
    void send(EmailAdapter adapter)
}