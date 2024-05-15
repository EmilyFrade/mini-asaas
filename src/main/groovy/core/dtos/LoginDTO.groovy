package core.dtos

class LoginDTO {
    String email
    String password

    public LoginDTO(Map params) {
        this.email = params.email
        this.password = params.password
    }

}
