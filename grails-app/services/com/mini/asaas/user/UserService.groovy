package com.mini.asaas.user

import com.mini.asaas.exceptions.BusinessException
import com.mini.asaas.user.adapters.SaveUserAdapter
import com.mini.asaas.user.adapters.UpdateUserAdapter
import com.mini.asaas.user.adapters.UpdateUserPasswordAdapter
import com.mini.asaas.utils.DomainErrorUtils
import com.mini.asaas.validation.BusinessValidation
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService

@Transactional
class UserService {

    SpringSecurityService springSecurityService

    UserRoleService userRoleService

    public User show() {
        User user = springSecurityService.loadCurrentUser() as User
        if (!user) throw new RuntimeException("Usuário não encontrado")

        return user
    }

    public User save(SaveUserAdapter adapter) {
        User validatedUser = validateBeforeSave(adapter)
        if (validatedUser.hasErrors()) {
            throw new BusinessException(DomainErrorUtils.getFirstValidationMessage(validatedUser))
        }

        User user = buildUser(adapter, new User())
        user.save(failOnError: true)

        userRoleService.save(adapter.roleAuthority, user)

        return user
    }

    public User update(UpdateUserAdapter adapter) {
        User user = springSecurityService.loadCurrentUser() as User
        if (!user) throw new RuntimeException("Usuário não encontrado")

        if (validateBeforeUpdate(adapter, user).hasErrors()) {
            throw new BusinessException(DomainErrorUtils.getFirstValidationMessage(user))
        }

        user.name = adapter.name
        user.email = adapter.email
        user.save(failOnError: true)

        userRoleService.save(adapter.roleAuthority, user)

        return user
    }

    public User updatePassword(UpdateUserPasswordAdapter adapter) {
        User user = springSecurityService.loadCurrentUser() as User
        if (!user) throw new RuntimeException("Usuário não encontrado")

        user = validateBeforeUpdatePassword(adapter, user)
        if (user.hasErrors()) {
            throw new BusinessException(DomainErrorUtils.getFirstValidationMessage(user))
        }

        user.password = adapter.newPassword
        user.save(failOnError: true)

        return user
    }

    private User validateBeforeSave(SaveUserAdapter adapter) {
        User user = new User();
        UserValidator validator = new UserValidator()
        BusinessValidation validationResult = validator.validateBeforeSave(adapter)
        if (!validationResult.isValid()) {
            DomainErrorUtils.addBusinessRuleErrors(user, validationResult.errors)
        }

        return user
    }

    private User validateBeforeUpdate(UpdateUserAdapter adapter, User user) {
        UserValidator validator = new UserValidator()
        BusinessValidation validationResult = validator.validateBeforeUpdate(adapter, user)
        if (!validationResult.isValid()) {
            DomainErrorUtils.addBusinessRuleErrors(user, validationResult.errors)
        }

        return user
    }

    private User validateBeforeUpdatePassword(UpdateUserPasswordAdapter adapter, User user) {
        UserValidator validator = new UserValidator()
        BusinessValidation validationResult = validator.validateBeforeUpdatePassword(adapter, user)
        if (!validationResult.isValid()) {
            DomainErrorUtils.addBusinessRuleErrors(user, validationResult.errors)
        }

        return user
    }

    private User buildUser(SaveUserAdapter adapter, User user) {
        user.name = adapter.name
        user.email = adapter.email
        user.password = adapter.password

        return user
    }

}
