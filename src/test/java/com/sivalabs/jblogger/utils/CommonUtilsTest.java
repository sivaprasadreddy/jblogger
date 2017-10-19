package com.sivalabs.jblogger.utils;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


public class CommonUtilsTest {
    @Test
    public void getStartOfDay() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        final LocalDateTime startOfDay = CommonUtils.getStartOfDay(now);

        assertThat(now.getYear()).isEqualTo(startOfDay.getYear());
        assertThat(now.getMonth()).isEqualTo(startOfDay.getMonth());
        assertThat(now.getDayOfYear()).isEqualTo(startOfDay.getDayOfYear());

        assertThat(startOfDay.getHour()).isEqualTo(0);
        assertThat(startOfDay.getMinute()).isEqualTo(0);
        assertThat(startOfDay.getSecond()).isEqualTo(0);
    }

    @Test
    public void getEndOfDay() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        final LocalDateTime endOfDay = CommonUtils.getEndOfDay(now);

        assertThat(now.getYear()).isEqualTo(endOfDay.getYear());
        assertThat(now.getMonth()).isEqualTo(endOfDay.getMonth());
        assertThat(now.getDayOfYear()).isEqualTo(endOfDay.getDayOfYear());

        assertThat(endOfDay.getHour()).isEqualTo(23);
        assertThat(endOfDay.getMinute()).isEqualTo(59);
        assertThat(endOfDay.getSecond()).isEqualTo(59);
    }

    @Test
    public void getYesterDay() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        final LocalDateTime yesterDay = CommonUtils.getYesterDay(now);
        assertThat(now).isEqualTo(yesterDay.plusDays(1));
    }

    @Test
    public void getWeekStartDay() throws Exception {
        LocalDateTime now = LocalDateTime.of(2017,10,19,10,10,20);
        LocalDateTime expected = LocalDateTime.of(2017,10,16,10,10,20);
        final LocalDateTime weekStartDay = CommonUtils.getWeekStartDay(now);
        assertThat(weekStartDay).isEqualTo(expected);
    }

    @Test
    public void getWeekEndDay() throws Exception {
        LocalDateTime now = LocalDateTime.of(2017,10,19,10,10,20);
        LocalDateTime expected = LocalDateTime.of(2017,10,22,10,10,20);
        final LocalDateTime weekEndDay = CommonUtils.getWeekEndDay(now);
        assertThat(weekEndDay).isEqualTo(expected);
    }

    @Test
    public void getMonthStartDay() throws Exception {
        LocalDateTime now = LocalDateTime.of(2017,10,19,10,10,20);
        LocalDateTime expected = LocalDateTime.of(2017,10,1,10,10,20);
        final LocalDateTime monthStartDay = CommonUtils.getMonthStartDay(now);
        assertThat(monthStartDay).isEqualTo(expected);
    }

    @Test
    public void getMonthEndDay() throws Exception {
        LocalDateTime now = LocalDateTime.of(2017,10,19,10,10,20);
        LocalDateTime expected = LocalDateTime.of(2017,10,31,10,10,20);
        final LocalDateTime monthEndDay = CommonUtils.getMonthEndDay(now);
        assertThat(monthEndDay).isEqualTo(expected);
    }

    @Test
    public void getDummyVeryOldDate() throws Exception {
        LocalDateTime expected = LocalDateTime.of(1900,1,1,1,1,1,1);
        final LocalDateTime veryOldDate = CommonUtils.getDummyVeryOldDate();
        assertThat(veryOldDate).isEqualTo(expected);
    }

    @Test
    public void getDummyVeryNewDate() throws Exception {
        LocalDateTime expected = LocalDateTime.of(9999,1,31,23,59,59,1);
        final LocalDateTime veryNewDate = CommonUtils.getDummyVeryNewDate();
        assertThat(veryNewDate).isEqualTo(expected);
    }

}
