package com.mini.asaas.user


import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_SELLER"])
class UserController {

    UserService userService

    static allowedMethods = [show: "GET"]

    def show() {
        try {
            User user = userService.show()
            render(view: "show", model: [user: user])
        } catch (Exception e) {
            redirect(uri: "/logout")
        }
    }

}
