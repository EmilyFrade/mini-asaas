package com.mini.asaas.user

import com.mini.asaas.exceptions.BusinessException
import com.mini.asaas.repository.UserRepository
import com.mini.asaas.utils.MessageSourceUtils
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService
import org.springframework.security.crypto.password.PasswordEncoder

@Transactional
class UserService {

    SpringSecurityService springSecurityService

    public User login(LoginUserAdapter adapter) {
        User user = validateBeforeLogin(adapter)
        springSecurityService.reauthenticate(user.email, user.password)
        return user
    }

    public User show() {
        User user = springSecurityService.loadCurrentUser() as User
        if (!user) throw new RuntimeException("Usuário não encontrado")
        return user
    }

    private User validateBeforeLogin(LoginUserAdapter adapter) {
        User validatedUser = UserRepository.findByEmail(adapter.email)

        if (!validatedUser) {
            throw new BusinessException(MessageSourceUtils.getMessage("login.not.found"))
        }

        PasswordEncoder passwordEncoder = springSecurityService.passwordEncoder as PasswordEncoder

        if (!passwordEncoder.matches(adapter.password, validatedUser.password)) {
            throw new BusinessException(MessageSourceUtils.getMessage("login.not.found"))
        }

        return validatedUser
    }

}
