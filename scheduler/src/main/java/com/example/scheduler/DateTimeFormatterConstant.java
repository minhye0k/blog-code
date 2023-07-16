package com.example.scheduler;

import java.time.format.DateTimeFormatter;

public class DateTimeFormatterConstant {

    private static final String DATE_TIME_FORMAT_PATTERN = "HH:mm:ss";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_PATTERN);
}
