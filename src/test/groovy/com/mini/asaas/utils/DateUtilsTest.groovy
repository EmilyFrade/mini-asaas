package com.mini.asaas.utils

import spock.lang.Specification

import java.text.SimpleDateFormat

class DateUtilsTest extends Specification {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy")

    private static Date getDate(String date) {
        return DATE_FORMAT.parse(date)
    }

    void "it should return the difference in years between two dates"() {
        expect:
            DateUtils.getDifferenceInYears(getDate(startDate), getDate(endDate)) == expectedYears

        where:
            startDate       | endDate               | expectedYears
            "01/01/2000"    | "31/12/2000"          | 0
            "01/01/2000"    | "01/01/2001"          | 1
            "01/01/2000"    | "01/01/2002"          | 2
            "01/01/2000"    | "01/01/2003"          | 3
            "16/05/2000"    | "15/05/2001"          | 0
            "16/05/2000"    | "16/05/2001"          | 1
            "16/05/2000"    | "17/05/2001"          | 1
    }
}
