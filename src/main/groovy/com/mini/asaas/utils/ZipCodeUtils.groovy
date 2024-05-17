package com.mini.asaas.utils

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class ZipCodeUtils {

    public static boolean isValid(String zipCode, boolean formatted = false) {
        if (zipCode == null) return false
        String regex = formatted ? /\d{5}-\d{3}/ : /\d{8}/
        return zipCode.matches(regex)
    }

    public static String applyMask(String zipCode) {
        if (!isValid(zipCode)) {
            throw new IllegalArgumentException("O CEP deve conter apenas 8 dígitos numéricos para ser formatado corretamente.")
        }
        return zipCode.replaceAll(/(\d{5})(\d{3})/, '$1-$2')
    }
}
