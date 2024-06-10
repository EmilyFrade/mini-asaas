package com.mini.asaas.auth

import com.mini.asaas.exceptions.BusinessException
import com.mini.asaas.user.User
import com.mini.asaas.user.UserRepository
import com.mini.asaas.user.adapters.LoginUserAdapter
import com.mini.asaas.utils.MessageSourceUtils
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService
import org.springframework.security.crypto.password.PasswordEncoder

@Transactional
class AuthService {

    SpringSecurityService springSecurityService

    public User login(LoginUserAdapter adapter) {
        User user = validateBeforeLogin(adapter)
        springSecurityService.reauthenticate(user.email, user.password)

        return user
    }

    public User validateBeforeLogin(LoginUserAdapter adapter) {
        User validatedUser = UserRepository.query([email: adapter.email]).get()

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
