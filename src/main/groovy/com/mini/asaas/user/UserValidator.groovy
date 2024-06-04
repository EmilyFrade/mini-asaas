package com.mini.asaas.user

import com.mini.asaas.base.BaseValidator
import com.mini.asaas.user.adapters.SaveUserAdapter
import com.mini.asaas.user.adapters.UpdateUserAdapter
import com.mini.asaas.utils.EmailUtils
import com.mini.asaas.validation.BusinessValidation

class UserValidator extends BaseValidator {

    private static final Integer MIN_PASSWORD_LENGTH = 8

    public BusinessValidation validateBeforeSave(SaveUserAdapter adapter) {
        validateEmail(adapter.email)
        validateIfEmailExists(adapter.email)
        validatePassword(adapter.password)
        validateAuthority(adapter.roleAuthority)

        return validationResult
    }

    public BusinessValidation validateBeforeUpdate(UpdateUserAdapter adapter, User user) {
        validateEmail(adapter.email)
        if (user.email != adapter.email) validateIfEmailExists(adapter.email)
        if (user.getMainAuthority() != adapter.roleAuthority) validateIfCanUpdateAuthority(user)

        return validationResult
    }

    private UserValidator validateEmail(String email) {
        if (!EmailUtils.isValid(email)) {
            validationResult.addError("invalid.email")
        }

        return this
    }

    private UserValidator validateIfEmailExists(String email) {
        if (email && UserRepository.query([email: email]).exists()) {
            validationResult.addError("user.alreadyExists.email")
        }

        return this
    }

    private UserValidator validateAuthority(RoleAuthority authority) {
        if (!authority) {
            validationResult.addError("user.authority.invalid")
        }

        return this
    }

    private UserValidator validatePassword(String password) {
        if (!password) {
            validationResult.addError("user.password.blank")
        }
        if (password.length() < 8) {
            validationResult.addError("user.password.min.length", [MIN_PASSWORD_LENGTH])
        }
        if (!password.matches(".*[0-9].*")) {
            validationResult.addError("user.password.mustContainNumber")
        }
        if (!password.matches(".*[A-Z].*")) {
            validationResult.addError("user.password.mustContainUppercase")
        }
        if (!password.matches(".*[a-z].*")) {
            validationResult.addError("user.password.mustContainLowercase")
        }
        if (!password.matches(".*[!@#\$%^&*].*")) {
            validationResult.addError("user.password.mustContainSpecialCharacter")
        }

        return this
    }

    private UserValidator validateIfCanUpdateAuthority(User user) {
        if (!user.isAdmin()) {
            validationResult.addError("user.authority.update.not.allowed")
        }

        return this
    }

}
