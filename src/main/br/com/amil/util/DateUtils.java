package br.com.amil.util;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.parse;

import static java.time.format.DateTimeFormatter.ofPattern;

public class DateUtils {

    private DateUtils() {
    }

    public static String DEFAULT_DATETIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

    public static LocalDateTime parseToLocalDateTime(String localDateTime) {
        return parse(localDateTime, ofPattern(DEFAULT_DATETIME_FORMAT));
    }
}
