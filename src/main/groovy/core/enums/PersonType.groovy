package core.enums

import grails.util.Holders

/**
 * <p>Enumeração que representa os tipos de pessoa.</p>
 * <p>Os valores possíveis são:</p>
 * <ul>
 *     <li>NATURAL</li>
 *     <li>LEGAL</li>
 * </ul>
 */
enum PersonType {
    NATURAL,
    LEGAL

    /**
     * Obtém a label internacionalizada do tipo de pessoa.
     */
    String getLabel() {
        Locale locale = new Locale("pt", "BR")
        String messageCode = "personType.${this.name()}.label"
        return Holders
                .applicationContext
                .getBean('messageSource')
                .getMessage(messageCode, null, "", locale)
    }

    /**
     * Converte uma string para um tipo de pessoa.
     * @param value Valor a ser convertido ("natural" ou "legal"), case insensitive.
     * @return {@code PersonType} se o valor informado corresponder a um tipo de pessoa, ou
     *         {@code null} se o valor informado não corresponder a nenhum tipo de pessoa.
     */
    static PersonType fromString(String value) {
        try {
            return value instanceof String ? valueOf(value.toUpperCase()) : null
        } catch (IllegalArgumentException e) {
            return null
        }
    }

    static PersonType fromCpfCnpj(String cpfCnpj) {
        switch (cpfCnpj.length()) {
            case 11: return NATURAL
            case 14: return LEGAL
            default: throw new IllegalArgumentException("O valor não é um CPF/CNPJ válido")
        }
    }
}