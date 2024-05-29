package com.mini.asaas.utils

import spock.lang.Specification
import spock.lang.Unroll

import java.text.SimpleDateFormat

class DateFormatUtilsTest extends Specification {

    @Unroll
    def "it should format the date #date using the format #format"() {
        expect:
            DateFormatUtils.format(date, format) == expectedDate

        where:
            date         | format               | expectedDate
            "25/12/2021" | "dd/MM/yyyy"         | new SimpleDateFormat("dd/MM/yyyy").parse("25/12/2021")
            "2021-12-25" | "yyyy-MM-dd"         | new SimpleDateFormat("yyyy-MM-dd").parse("2021-12-25")
            null         | "dd/MM/yyyy"         | null
            ""           | "dd/MM/yyyy"         | null
    }

    @Unroll
    def "it should format the date #date using the default format"() {
        expect:
            DateFormatUtils.format(date) == expectedDate

        where:
            date         | expectedDate
            "25/12/2021" | new SimpleDateFormat("dd/MM/yyyy").parse("25/12/2021")
            null         | null
            ""           | null
    }

}
