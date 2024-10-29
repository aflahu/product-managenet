package com.example.productManagement.helper;

import com.example.productManagement.constant.DateConstant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

public class DateHelper {

    public static String convertDatePatternToString(Date date, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String convertInstantToStringPattern(Instant instant, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern)
                .withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }

    public static String convertInstantToStringPatternWithZoneId(Instant instant, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern)
                .withZone(ZoneId.of("UTC+0"));
        return formatter.format(instant);
    }

    public static LocalDateTime convertStringToLocalDateTime(String inputDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateConstant.DATE_FORMAT_1, Locale.ROOT);
        return LocalDateTime.parse(inputDate, formatter);
    }

    public static LocalDateTime localDateTimeIso8601() {
        Instant instant = Instant.now();
        DateTimeFormatter isoFormat = DateTimeFormatter.ISO_DATE_TIME;
        return LocalDateTime.parse(instant.toString(), isoFormat);
    }

    public static LocalDateTime localDateTimeIso8601MinusSpecificNumber(ChronoUnit chronoUnit, Integer number) {
        Instant instant = Instant.now().minus(number, chronoUnit);
        DateTimeFormatter isoFormat = DateTimeFormatter.ISO_DATE_TIME;
        return LocalDateTime.parse(instant.toString(), isoFormat);
    }
}
