package com.mini.asaas.utils

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class StringUtils {

    public static String removeNonNumeric(String value) {
        if (value == null) return null
        return value.replaceAll("[^0-9]", "")
    }

    public static String pascalToCamelCase(String value) {
        if (!value) return value
        return value.substring(0, 1).toLowerCase() + value.substring(1)
    }

}
