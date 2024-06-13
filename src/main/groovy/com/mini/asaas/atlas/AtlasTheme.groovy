package com.mini.asaas.atlas

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
enum AtlasTheme {
    PRIMARY,
    SECONDARY,
    SUCCESS,
    DANGER,
    WARNING,
    HIGHLIGHT

    public String getValue() {
        if (this == null) return null
        return this.name().toLowerCase()
    }
}
