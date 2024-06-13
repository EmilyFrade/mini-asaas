package com.mini.asaas.email

import grails.compiler.GrailsCompileStatic

import java.util.concurrent.Future

@GrailsCompileStatic
interface SendEmailService {
    Future<Void> send(EmailMessage emailMessage)
}