package com.shadoxy.logging;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogFormater {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private static final String STRING_FORMAT = "%s [%-20s] %-5s %s - %s";

    public String format(ShadoxyLogLevel logLevel, String loggerName, String message) {
        return String.format(STRING_FORMAT,
                LocalDateTime.now().format(FORMATTER),
                Thread.currentThread().getName(),
                logLevel,
                loggerName,
                message);
    }
}
