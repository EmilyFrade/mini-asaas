package com.mini.asaas.base

import com.mini.asaas.validation.BusinessValidation

abstract class BaseValidator {

    private BusinessValidation validationResult

    public BaseValidator() {
        this.validationResult = new BusinessValidation()
    }

}
