package com.mini.asaas

import core.dtos.LoginDTO
import core.exceptions.DomainException
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService
import org.springframework.security.crypto.password.PasswordEncoder

/**
 * Serviço responsável por autenticar usuários.
 */
@Transactional
class AuthService {

    SpringSecurityService springSecurityService

    /**
     * Realiza o login do usuário.
     * @param loginDTO DTO com os dados de login.
     * @return Usuário logado.
     *
     * @throws DomainException Caso não seja possível encontrar um usuário com o e-mail informado.
     * @throws DomainException Caso a senha informada não seja válida.
     *
     * @see User
     * @see LoginDTO
     */
    User login(LoginDTO loginDTO) {
        User user = User.findByEmail(loginDTO.email)

        if (user == null) {
            throw new DomainException("Desculpe, não foi possível encontrar um usuário com este e-mail e senha.")
        }

        PasswordEncoder passwordEncoder = springSecurityService.passwordEncoder as PasswordEncoder

        if (!passwordEncoder.matches(loginDTO.password, user.password)) {
            throw new DomainException("Desculpe, não foi possível encontrar um usuário com este e-mail e senha.")
        }

        springSecurityService.reauthenticate(user.email, user.password)

        return user
    }

}
