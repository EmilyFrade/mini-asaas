package com.mini.asaas.utils

class Utils {
    public static Map<String, String> normalizeParams(Map params) {

        if (params == null) return [:]

        Map<String, String> normalizeParams = [:]

        for (String key : params.keySet()) {
            normalizeParams[key] = StringUtils.ensureStringAndTrim(params[key])
        }

        return normalizeParams
    }
}