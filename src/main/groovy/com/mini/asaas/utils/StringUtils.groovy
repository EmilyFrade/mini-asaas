package com.mini.asaas.utils

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class StringUtils {

    public static String removeNonNumeric(String value) {
        if (value == null) return null
        return value.replaceAll("[^0-9]", "")
    }

    public static String formatFromCommaToDot(String value) {
        if (value == null) return null
        value.replace(".", "")
        return value.replace(",", ".")
    }

    public static String formatFromDotToComma(String value) {
        if (value == null) return null
        return value.replace(".", ",")
    }

    public static String pascalToCamelCase(String value) {
        if (!value) return value
        return value.substring(0, 1).toLowerCase() + value.substring(1)
    }

    public static String ensureStringAndTrim(Object value) {
        if (!value || !(value instanceof String)) return null
        String trimmedValue = (value as String).trim()
        return trimmedValue.isEmpty() ? null : trimmedValue
    }

}
