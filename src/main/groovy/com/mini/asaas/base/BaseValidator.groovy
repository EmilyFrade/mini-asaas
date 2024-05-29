package com.mini.asaas.base

import com.mini.asaas.validation.BusinessValidation

abstract class BaseValidator {

    final BusinessValidation validationResult

    public BaseValidator() {
        this.validationResult = new BusinessValidation()
    }

}
