package com.mini.asaas.utils

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class DateUtils {

    public static Calendar getInstanceOfCalendar(Date date) {
        if (!date) return null
        Calendar calendar = Calendar.getInstance()
        calendar.setTime(date)
        return calendar
    }

    public static Integer getDifferenceInYears(Date startDate, Date endDate) {
        Calendar startCalendar = getInstanceOfCalendar(startDate)
        Calendar endCalendar = getInstanceOfCalendar(endDate)

        Integer years = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR)

        Boolean startMonthIsAfterEndMonth = startCalendar.get(Calendar.MONTH) > endCalendar.get(Calendar.MONTH)
        Boolean startMonthIsEqualEndMonth = startCalendar.get(Calendar.MONTH) == endCalendar.get(Calendar.MONTH)
        Boolean startDayIsAfterEndDay = startCalendar.get(Calendar.DAY_OF_MONTH) > endCalendar.get(Calendar.DAY_OF_MONTH)

        if (startMonthIsAfterEndMonth || (startMonthIsEqualEndMonth && startDayIsAfterEndDay)) {
            years--
        }

        return years
    }

}
