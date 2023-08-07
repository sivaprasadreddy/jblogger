package com.sivalabs.jblogger.utils;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class CommonUtils {
    public static LocalDateTime getStartOfDay(LocalDateTime date) {
        return date.toLocalDate().atStartOfDay();
    }

    public static LocalDateTime getEndOfDay(LocalDateTime date) {
        return date.toLocalDate().atTime(23, 59, 59);
    }

    public static LocalDateTime getYesterday(LocalDateTime date) {
        return date.minusDays(1);
    }

    public static LocalDateTime getWeekStartDay(LocalDateTime date) {
        return date.with(DayOfWeek.MONDAY);
    }

    public static LocalDateTime getWeekEndDay(LocalDateTime date) {
        return date.with(DayOfWeek.SUNDAY);
    }

    public static LocalDateTime getMonthStartDay(LocalDateTime date) {
        return date.with(firstDayOfMonth());
    }

    public static LocalDateTime getMonthEndDay(LocalDateTime date) {
        return date.with(lastDayOfMonth());
    }

    public static LocalDateTime getDummyVeryOldDate() {
        return LocalDateTime.now()
                .withYear(1900)
                .withMonth(1)
                .withDayOfYear(1)
                .withHour(1)
                .withMinute(1)
                .withSecond(1)
                .withNano(1);
    }

    public static LocalDateTime getDummyVeryNewDate() {
        return LocalDateTime.now()
                .withYear(9999)
                .withMonth(12)
                .withDayOfYear(31)
                .withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(1);
    }
}
