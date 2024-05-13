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

}
