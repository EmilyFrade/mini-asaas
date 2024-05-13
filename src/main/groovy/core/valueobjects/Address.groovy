package core.valueobjects

/**
 * <p>Objeto de valor que representa um endereço completo de uma pessoa.</p>
 * <p>Todos os atributos são obrigatórios, exceto o complemento.</p>
 * <p>O {@code zipCode} deve ser uma string de 8 caracteres numéricos, sem formatação.</p>
 * <p>O {@code ufState} deve ser uma sigla de 2 caracteres alfabéticos e maiúsculos.</p>
 * <p>O {@code number} deve ser um número inteiro positivo ou nulo (caso o endereço não possua número).</p>
 */
class Address {

    String street
    Integer number
    String complement
    String neighborhood
    String city
    String ufState
    String zipCode

    static constraints = {
        street blank: false, nullable: false
        number blank: false, nullable: true, min: 1
        complement nullable: true
        neighborhood blank: false, nullable: false
        city blank: false, nullable: false
        ufState blank: false, nullable: false, matches: /[A-Z]{2}/
        zipCode blank: false, nullable: false, matches: /\d{8}/
    }

}
