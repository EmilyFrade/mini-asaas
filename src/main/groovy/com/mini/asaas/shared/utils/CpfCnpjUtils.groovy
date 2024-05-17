package com.mini.asaas.shared.utils

class CpfCnpjUtils {

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
        return cpfCnpj.collect { it.toInteger() }
    }

    private static int calculateDigit(List<Integer> numbers, int length) {
        int sum = 0

        for (int i = 0; i < length; i++) {
            sum += numbers[i] * (length + 1 - i)
        }

        int expectedDigit = (sum * 10) % 11
        return expectedDigit == 10 ? 0 : expectedDigit
    }

    private static boolean isValidDigits(String cpfCnpj) {
        List<Integer> numbers = getDigits(cpfCnpj)

        int secondCheckDigitIndex = cpfCnpj.length() - 1
        int firstCheckDigitIndex = secondCheckDigitIndex - 1

        if (numbers[firstCheckDigitIndex] != calculateDigit(numbers, firstCheckDigitIndex)) return false
        return numbers[secondCheckDigitIndex] == calculateDigit(numbers, secondCheckDigitIndex)
    }
}
