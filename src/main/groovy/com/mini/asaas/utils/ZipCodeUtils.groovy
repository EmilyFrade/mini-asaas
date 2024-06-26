package com.mini.asaas.utils

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class ZipCodeUtils {

    public static Boolean isValid(String zipCode, Boolean formatted) {
        if (zipCode == null) return false
        String regex = formatted ? /\d{5}-\d{3}/ : /\d{8}/
        return zipCode.matches(regex)
    }

}
