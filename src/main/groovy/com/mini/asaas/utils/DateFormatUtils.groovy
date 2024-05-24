package com.mini.asaas.utils

import grails.compiler.GrailsCompileStatic

import java.text.SimpleDateFormat

@GrailsCompileStatic
class DateFormatUtils {

    private static final String DEFAULT_FORMAT = "dd/MM/yyyy"

    public static Date parseFromString(String date, String format) {
        if (!date || !format) return null
        return new SimpleDateFormat(format).parse(date)
    }

    public static Date parseFromString(String date) {
        return parseFromString(date, DEFAULT_FORMAT)
    }

    public static String format(Date databaseDate) {
        if (!databaseDate) return null

        return new SimpleDateFormat(DEFAULT_FORMAT).format(databaseDate)
    }
}
