package com.mini.asaas.email

import com.mini.asaas.utils.DateFormatUtils
import com.mini.asaas.utils.Utils
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class EmailAdapter {

    String from

    String fromName

    String to

    String replyTo

    String subject

    String body

    Boolean isHTML = false

    Date sentDate

    public EmailAdapter(Map originalParams) {
        Map<String, String> params = Utils.normalizeParams(originalParams)
        if (!params) return
        this.from = params.from
        this.fromName = params.fromName
        this.to = params.to
        this.replyTo = params.replyTo
        this.subject = params.subject
        this.body = params.body
        this.isHTML = params.isHTML?.toBoolean() ?: false
        this.sentDate = DateFormatUtils.parseDateFromString(params.sentDate)
    }
}
