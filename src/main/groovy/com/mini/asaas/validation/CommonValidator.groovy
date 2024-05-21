package com.mini.asaas.validation

import com.mini.asaas.base.BaseValidator

class CommonValidator extends BaseValidator {

    public BusinessValidation notNull(Object value, String code) {
        BusinessValidation validation = new BusinessValidation()
        String defaultCode = getErrorCode("notNull")
        if (value == null) validation.addError(code ?: defaultCode)
        return validation
    }

    public BusinessValidation notBlank(String value, String code) {
        BusinessValidation validation = new BusinessValidation()
        String defaultCode = getErrorCode("notBlank")
        if (value == null || value.trim().isEmpty()) validation.addError(code ?: defaultCode)
        return validation
    }

    public BusinessValidation notEmpty(Collection list, String code) {
        BusinessValidation validation = new BusinessValidation()
        String defaultCode = getErrorCode("notEmpty")
        if (list == null || list.isEmpty()) validation.addError(code ?: defaultCode)
        return validation
    }
}
