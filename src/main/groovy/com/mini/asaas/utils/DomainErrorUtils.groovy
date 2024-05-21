package com.mini.asaas.utils

import org.grails.datastore.gorm.GormEntity
import org.springframework.validation.ObjectError

class DomainErrorUtils {

    public static Object addError(GormEntity entity, String message) {
        entity.errors.reject("", null, message)
        return entity
    }

    public static Object addError(GormEntity entity, String code, String message) {
        entity.errors.reject(code, null, message)
        return entity
    }

    public static Object addFieldError(GormEntity entity, String field, String code, Object[] args) {
        entity.errors.rejectValue(field, code, args, "")
        return entity
    }

    public static Object addFieldError(GormEntity entity, String field, String code) {
        entity.errors.rejectValue(field, code)
        return entity
    }

    public static String getFirstValidationMessage(GormEntity entity) {
        ObjectError error = entity.errors.allErrors.first()
        return error.defaultMessage ?: MessageSourceUtils.getMessage(error.codes.first(), error.arguments as List)
    }

    public static Boolean hasErrorCode(GormEntity entity, String code) {
        List<ObjectError> errors = entity.errors.allErrors
        return errors.any { it.codes.contains(code) }
    }

}
