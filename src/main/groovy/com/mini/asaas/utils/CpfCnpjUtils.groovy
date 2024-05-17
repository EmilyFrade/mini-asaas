package com.mini.asaas.utils

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class CpfCnpjUtils {

    public static final int CPF_LENGTH = 11
    public static final int CNPJ_LENGTH = 14

    public static boolean isValidCPF(String cpf) {
        String cleanedCpf = removeMask(cpf)
        if (allDigitsAreEqual(cleanedCpf) || !onlyDigits(cleanedCpf) || !isValidCPFLength(cleanedCpf)) return false
        return isValidDigits(cleanedCpf)
    }

    public static boolean isValidCNPJ(String cnpj) {
        String cleanedCnpj = removeMask(cnpj)
        if (allDigitsAreEqual(cleanedCnpj) || !onlyDigits(cleanedCnpj) || !isValidCNPJLength(cleanedCnpj)) return false
        return isValidDigits(cleanedCnpj)
    }

    public static String removeMask(String cpfCnpj) {
        return cpfCnpj.replaceAll("[^0-9]", "")
    }

    public static String applyMask(String cpfCnpj) {
        if (isValidCPFLength(cpfCnpj)) {
            return cpfCnpj.replaceAll(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4')
        }

        if (isValidCNPJLength(cpfCnpj)) {
            return cpfCnpj.replaceAll(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/, '$1.$2.$3/$4-$5')
        }

        throw new IllegalArgumentException("O valor não é um CPF/CNPJ válido ou está em um formato inválido.")
    }

    private static boolean onlyDigits(String cpfCnpj) {
        return cpfCnpj.matches("\\d+")
    }

    private static boolean allDigitsAreEqual(String cpfCnpj) {
        return cpfCnpj.matches("(\\d)\\1*")
    }

    private static boolean isValidCPFLength(String cpf) {
        return cpf != null && cpf.length() == CPF_LENGTH
    }

    private static boolean isValidCNPJLength(String cnpj) {
        return cnpj != null && cnpj.length() == CNPJ_LENGTH
    }

    private static List<Integer> getDigits(String cpfCnpj) {
        if (cpfCnpj == null) return []
        return cpfCnpj.collect { String digit -> digit.toInteger() }
    }

    private static boolean isValidDigits(String cpfCnpj) {
        List<Integer> digits = getDigits(cpfCnpj)

        int firstCheckDigitIndex = digits.size() - 2
        int secondCheckDigitIndex = digits.size() - 1

        if (digits.size() == CPF_LENGTH) {
            if (digits[firstCheckDigitIndex] != calculateDigitFromCPF(digits, firstCheckDigitIndex)) return false
            return digits[secondCheckDigitIndex] == calculateDigitFromCPF(digits, secondCheckDigitIndex)
        }

        if (digits.size() == CNPJ_LENGTH) {
            if (digits[firstCheckDigitIndex] != calculateDigitFromCNPJ(digits, firstCheckDigitIndex)) return false
            return digits[secondCheckDigitIndex] == calculateDigitFromCNPJ(digits, secondCheckDigitIndex)
        }

        return false
    }

    private static int calculateDigitFromCPF(List<Integer> digits, int checkDigitIndex) {
        int sum = 0
        int weight = checkDigitIndex + 1

        for (int i = 0; i < checkDigitIndex; i++) {
            sum += digits[i] * (weight - i)
        }

        int expectedDigit = 11 - (sum % 11)
        return expectedDigit >= 10 ? 0 : expectedDigit
    }

    private static int calculateDigitFromCNPJ(List<Integer> digits, int checkDigitIndex) {
        int sum = 0
        int weight = checkDigitIndex - 7

        for (int i = 0; i < checkDigitIndex; i++) {
            sum += digits[i] * weight
            weight = weight == 2 ? 9 : weight - 1
        }

        int expectedDigit = 11 - (sum % 11)
        return expectedDigit >= 10 ? 0 : expectedDigit
    }
}
