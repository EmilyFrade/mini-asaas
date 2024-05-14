package core.dtos

class CreateCustomerDTO {

    String name
    String email
    String cpfCnpj
    String phoneNumber

    public CreateCustomerDTO(Map params) {
        this.name = params.name
        this.email = params.email
        this.cpfCnpj = params.cpfCnpj
        this.phoneNumber = params.phoneNumber
    }

}
