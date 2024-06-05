package com.mini.asaas.utils

class BigDecimalUtils {

    public static BigDecimal calculateInterestValue(BigDecimal originalValue, BigDecimal interestPercentual) {
        if (!originalValue || !interestPercentual) return null
        return (originalValue * interestPercentual) / 100
    }

    public static BigDecimal calculateDiscountValue(BigDecimal originalValue, BigDecimal discountPercentual) {
        if (!originalValue || !discountPercentual) return null
        return (originalValue * discountPercentual) / 100
    }

    public static BigDecimal calculateNetValue(BigDecimal originalValue, BigDecimal discountValue) {
        if (!originalValue || !discountValue) return null
        return originalValue - discountValue
    }

    public static BigDecimal fromFormattedString(String value) {
        try {
            return value ? new BigDecimal(value.replaceAll('\\.', "").replaceAll(',', ".")) : null
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Unparseable bigDecimal: \"${value}\" ")
        }
    }
}