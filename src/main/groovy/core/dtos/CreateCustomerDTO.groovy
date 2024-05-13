package core.dtos

class CreateCustomerDTO {

    final String name
    final String email
    final String cpfCnpj
    final String phoneNumber

    CreateCustomerDTO(Map<String, String> params) {
        this.name = params.name
        this.email = params.email
        this.cpfCnpj = params.cpfCnpj
        this.phoneNumber = params.phoneNumber
    }

    static constraints = {
        name nullable: false, blank: false
        email nullable: false, blank: false, email: true
        cpfCnpj nullable: false, blank: false, matches: "\\d{11}|\\d{14}"
        phoneNumber nullable: false, blank: false, matches: "(\\d{2})?\\d{4,5}-\\d{4}"
    }
}
