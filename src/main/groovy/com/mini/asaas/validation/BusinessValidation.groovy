package com.mini.asaas.validation

class BusinessValidation {

    List<BusinessRuleError> errors = []

    public Boolean isValid() {
        return this.errors.isEmpty()
    }

    public void addError(String code) {
        this.errors.add(new BusinessRuleError(code))
    }

    public void addError(String code, List args) {
        this.errors.add(new BusinessRuleError(code, args))
    }

    public void addErrors(List<BusinessRuleError> errors) {
        this.errors.addAll(errors)
    }

    public String getFirstErrorMessage() {
        return this.isValid() ? null : this.errors.first().getMessage()
    }

    public String getFirstErrorCode() {
        return this.isValid() ? null : this.errors.first().getCode()
    }

    public Boolean hasErrorCode(String code) {
        return this.errors.any { it.code == code }
    }
}
