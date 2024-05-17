package core.entities

import core.enums.PersonType
import core.valueobjects.Address

/**
 * <p>Classe abstrata que representa uma pessoa.</p>
 * <p>Essa classe contém os atributos comuns a todas as pessoas.</p>
 */
abstract class Person extends BaseEntity {

    String name
    String email
    String cpfCnpj
    String phoneNumber
    PersonType personType

    Address address

    static constraints = {
        name blank: false, nullable: false
        email email: true, blank: false, nullable: false
        cpfCnpj blank: false, nullable: false, size: 11..14
        phoneNumber blank: false, nullable: false
        personType blank: false, nullable: false
        address nullable: true
    }

    static embedded = ['address']

    static String cleanCpfCnpj(String cpfCnpj) {
        if (cpfCnpj.length() == 14 || cpfCnpj.length() == 18)
            return cpfCnpj.replaceAll("[^0-9]", "")
        else
            throw new IllegalArgumentException("O valor não é um CPF/CNPJ válido")
    }
}
