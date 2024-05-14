package core.dtos

class UpdateCustomerDTO {

    String name

    String email

    String cpfCnpj

    String phoneNumber

    public UpdateCustomerDTO(Map params) {
        this.name = params.name
        this.email = params.email
        this.cpfCnpj = params.cpfCnpj
        this.phoneNumber = params.phoneNumber
    }

}
