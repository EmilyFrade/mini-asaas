package com.mini.asaas.utils.person

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class CpfCnpjUtils {

    private static final int[] CPF_WEIGHTS_FIRST_CHECK = [10, 9, 8, 7, 6, 5, 4, 3, 2]
    private static final int[] CPF_WEIGHTS_SECOND_CHECK = [11, 10, 9, 8, 7, 6, 5, 4, 3, 2]
    private static final int[] CNPJ_WEIGHTS_FIRST_CHECK = [5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2]
    private static final int[] CNPJ_WEIGHTS_SECOND_CHECK = [6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2]

    private CpfCnpjUtils() {
        throw new IllegalStateException("Classe utilitária não deve ser instanciada.")
    }

    public static boolean isValidCPF(String CPF) {
        if (!isValidCPFLength(CPF) || allDigitsAreEqual(CPF)) return false
        return isValidDigits(CPF)
    }

    public static boolean isValidCNPJ(String CNPJ) {
        if (!isValidCNPJLength(CNPJ) || allDigitsAreEqual(CNPJ)) return false
        return isValidDigits(CNPJ)
    }

    public static String clean(String cpfCnpj) {
        return cpfCnpj.replaceAll("[^0-9]", "")
    }

    public static String format(String cpfCnpj) {
        if (isValidCPFLength(cpfCnpj)) {
            return cpfCnpj.replaceAll(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4')
        }

        if (isValidCNPJLength(cpfCnpj)) {
            return cpfCnpj.replaceAll(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/, '$1.$2.$3/$4-$5')
        }

        throw new IllegalArgumentException("O valor não é um CPF/CNPJ válido ou está em um formato inválido.")
    }

    private static boolean allDigitsAreEqual(String cpfCnpj) {
        return cpfCnpj.matches("(\\d)\\1{10}")
    }

    private static boolean isValidCPFLength(String CPF) {
        return CPF != null && CPF.matches("\\d{11}")
    }

    private static boolean isValidCNPJLength(String CNPJ) {
        return CNPJ != null && CNPJ.matches("\\d{14}")
    }

    private static List<Integer> getDigits(String cpfCnpj) {
        if (cpfCnpj == null) return []
        return cpfCnpj.collect { String digit -> digit.toInteger() }
    }

    private static int calculateDigit(List<Integer> numbers, int length, int[] weights) {
        int sum = 0

        for (int i = 0; i < length; i++) {
            sum += numbers[i] * weights[weights.length - length + i]
        }

        int expectedDigit = 11 - (sum % 11)
        return expectedDigit >= 10 ? 0 : expectedDigit
    }

    private static boolean isValidDigits(String cpfCnpj) {
        List<Integer> numbers = getDigits(cpfCnpj)

        int length = cpfCnpj.length()
        int firstCheckDigitIndex = length - 2
        int secondCheckDigitIndex = length - 1

        if (length == 11) {
            if (numbers[firstCheckDigitIndex] != calculateDigit(numbers, firstCheckDigitIndex, CPF_WEIGHTS_FIRST_CHECK)) return false
            return numbers[secondCheckDigitIndex] == calculateDigit(numbers, secondCheckDigitIndex, CPF_WEIGHTS_SECOND_CHECK)
        }

        if (length == 14) {
            if (numbers[firstCheckDigitIndex] != calculateDigit(numbers, firstCheckDigitIndex, CNPJ_WEIGHTS_FIRST_CHECK)) return false
            return numbers[secondCheckDigitIndex] == calculateDigit(numbers, secondCheckDigitIndex, CNPJ_WEIGHTS_SECOND_CHECK)
        }

        return false
    }
}
