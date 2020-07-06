package com.gzzz.utils;

/**
 * Date Utils
 * @author gzzz
 * @version 1.0.0
 */
public class DateUtils {
    public static boolean isLeapYear(int year) {
        return (year % 4 ==0 && year % 100 != 0) || year % 400 ==0;
    }

    public static int monthOfYear(int year, int month) {
        int [] leapMonths = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (!isLeapYear(year)) {
            leapMonths[1] = 28;
        }
        return leapMonths[month-1];
    }
}
