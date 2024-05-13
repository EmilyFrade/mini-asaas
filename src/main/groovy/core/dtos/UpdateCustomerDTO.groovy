package core.dtos

class UpdateCustomerDTO {

    final String name
    final String email
    final String cpfCnpj
    final String phoneNumber

    UpdateCustomerDTO(Map < String, String > params) {
        this.name = params.name
        this.email = params.email
        this.cpfCnpj = params.cpfCnpj
        this.phoneNumber = params.phoneNumber
    }

    static constraints = {
        name nullable: true, blank: false
        email nullable: true, blank: false, email: true
        cpfCnpj nullable: true, blank: false, matches: "\\d{11}|\\d{14}"
        phoneNumber nullable: true, blank: false, matches: "(\\d{2})?\\d{4,5}-\\d{4}"
    }
}
