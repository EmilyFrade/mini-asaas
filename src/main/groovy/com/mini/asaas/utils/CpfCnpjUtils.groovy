package com.mini.asaas.utils

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class CpfCnpjUtils {

    public static final Integer CPF_LENGTH = 11
    public static final Integer CNPJ_LENGTH = 14

    public static Boolean isCPF(String cpfCnpj) {
        if (cpfCnpj == null) return false
        String cleanedCpfCnpj = StringUtils.removeNonNumeric(cpfCnpj)
        return cleanedCpfCnpj.length() == CPF_LENGTH
    }

    public static Boolean isCNPJ(String cpfCnpj) {
        if (cpfCnpj == null) return false
        String cleanedCpfCnpj = StringUtils.removeNonNumeric(cpfCnpj)
        return cleanedCpfCnpj.length() == CNPJ_LENGTH
    }

    public static Boolean isValidCNPJ(String cnpj) {
        String cleanedCnpj = StringUtils.removeNonNumeric(cnpj)
        if (!simpleValidation(cleanedCnpj) || !isCNPJ(cleanedCnpj)) return false
        List<Integer> digits = getDigitsFromString(cleanedCnpj)
        Integer[] checkDigitIndexes = getIndexOfCheckDigits(cleanedCnpj)
        return cnpjCheckDigitsAreValid(digits, checkDigitIndexes)
    }

    public static Boolean isValidCPF(String cpf) {
        String cleanedCpf = StringUtils.removeNonNumeric(cpf)
        if (!simpleValidation(cleanedCpf) || !isCPF(cleanedCpf)) return false
        List<Integer> digits = getDigitsFromString(cleanedCpf)
        Integer[] checkDigitIndexes = getIndexOfCheckDigits(cleanedCpf)
        return cpfCheckDigitsAreValid(digits, checkDigitIndexes)
    }

    public static Boolean isValidCpfCnpj(String cpfCnpj) {
        String cleanedCpfCnpj = StringUtils.removeNonNumeric(cpfCnpj)
        if (!simpleValidation(cleanedCpfCnpj)) return false
        if (cleanedCpfCnpj.length() != CPF_LENGTH && cleanedCpfCnpj.length() != CNPJ_LENGTH) return false

        List<Integer> digits = getDigitsFromString(cleanedCpfCnpj)
        Integer[] checkDigitIndexes = getIndexOfCheckDigits(cleanedCpfCnpj)

        return cnpjCheckDigitsAreValid(digits, checkDigitIndexes) || cpfCheckDigitsAreValid(digits, checkDigitIndexes)
    }

    public static String formatCpfCnpj(String cpfCnpj) {
        if (isCPF(cpfCnpj)) return "${cpfCnpj[0..2]}.${cpfCnpj[3..5]}.${cpfCnpj[6..8]}-${cpfCnpj[9..10]}"
        else if (isCNPJ(cpfCnpj)) return "${cpfCnpj[0..1]}.${cpfCnpj[2..4]}.${cpfCnpj[5..7]}/${cpfCnpj[8..11]}-${cpfCnpj[12..13]}"

        return null
    }

    private static Boolean simpleValidation(String cpfCnpj) {
        return cpfCnpj != null && hasOnlyDigits(cpfCnpj) && !hasOnlyRepeatedDigits(cpfCnpj)
    }

    private static Boolean hasOnlyDigits(String cpfCnpj) {
        return cpfCnpj.matches("\\d+")
    }

    private static Boolean hasOnlyRepeatedDigits(String cpfCnpj) {
        return cpfCnpj.matches("(\\d)\\1*")
    }

    private static List<Integer> getDigitsFromString(String cpfCnpj) {
        if (cpfCnpj == null) return []
        return cpfCnpj.collect { String digit -> digit.toInteger() }
    }

    private static Integer[] getIndexOfCheckDigits(String cpfCnpj) {
        if (cpfCnpj == null) return new Integer[2]
        Integer length = cpfCnpj.length()
        return [length -2, length - 1] as Integer[]
    }

    private static Integer calculateCheckDigitFromCPF(List<Integer> digits, Integer checkDigitIndex) {
        Integer sum = 0
        Integer weight = checkDigitIndex + 1

        for (Integer i = 0; i < checkDigitIndex; i++) {
            sum += digits[i] * (weight - i)
        }

        Integer expectedDigit = 11 - (sum % 11)
        return expectedDigit >= 10 ? 0 : expectedDigit
    }

    private static Integer calculateCheckDigitFromCNPJ(List<Integer> digits, Integer checkDigitIndex) {
        Integer sum = 0
        Integer weight = checkDigitIndex - 7

        for (Integer i = 0; i < checkDigitIndex; i++) {
            sum += digits[i] * weight
            weight = weight == 2 ? 9 : weight - 1

        }

        Integer expectedDigit = 11 - (sum % 11)
        return expectedDigit >= 10 ? 0 : expectedDigit
    }

    private static Boolean cpfCheckDigitsAreValid(List<Integer> digits, Integer[] checkDigitIndexes) {
        if (digits[checkDigitIndexes[0]] != calculateCheckDigitFromCPF(digits, checkDigitIndexes[0])) return false
        return digits[checkDigitIndexes[1]] == calculateCheckDigitFromCPF(digits, checkDigitIndexes[1])
    }

    private static Boolean cnpjCheckDigitsAreValid(List<Integer> digits, Integer[] checkDigitIndexes) {
        if (digits[checkDigitIndexes[0]] != calculateCheckDigitFromCNPJ(digits, checkDigitIndexes[0])) return false
        return digits[checkDigitIndexes[1]] == calculateCheckDigitFromCNPJ(digits, checkDigitIndexes[1])
    }
}
