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

}
