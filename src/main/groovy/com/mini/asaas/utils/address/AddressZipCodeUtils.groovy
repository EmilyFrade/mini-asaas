package com.mini.asaas.utils.address

class AddressZipCodeUtils {

    private AddressZipCodeUtils() {
        throw new IllegalStateException("Classe utilitária não deve ser instanciada.")
    }

    public static boolean isValid(String zipCode, boolean formatted = false) {
        if (zipCode == null) return false
        String regex = formatted ? /\d{5}-\d{3}/ : /\d{8}/
        return zipCode.matches(regex)
    }

    public static String clean(String formattedZipCode) {
        if (!isValid(formattedZipCode, true)) {
            throw new IllegalArgumentException("O CEP deve estar no formato 'XXXXX-XXX' com para ser limpo corretamente.")
        }
        return formattedZipCode.replaceAll("-", "")
    }

    public static String format(String zipCode) {
        if (!isValid(zipCode)) {
            throw new IllegalArgumentException("O CEP deve conter apenas 8 dígitos numéricos para ser formatado corretamente.")
        }
        return zipCode.replaceAll(/(\d{5})(\d{3})/, '$1-$2')
    }
}
