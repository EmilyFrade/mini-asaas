package com.mini.asaas.base

import com.mini.asaas.utils.StringUtils

abstract class BaseValidator {

    final String prefix

    public BaseValidator() {
        this.prefix = StringUtils.pascalToCamelCase(this.getClass().simpleName)
    }

    protected getErrorCode(String code) {
        return "${prefix}.${code}.message"
    }

}
