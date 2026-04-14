package com.shadoxy.logging;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Implements the log formater for the console output.
 *
 * @author mmiss
 * @version 1.0
 */
public class LogFormater {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private static final String STRING_FORMAT = "%s [%-20s] %-5s %s - %s";

    /**
     * Format the string in the defined format.
     *
     * @param logLevel   current log level
     * @param loggerName current logger name
     * @param message    current message
     * @return formated string
     */
    public String format(ShadoxyLogLevel logLevel, String loggerName, String message) {
        StringBuilder stringBuilder = new StringBuilder();

        return stringBuilder.append(LocalDateTime.now().format(FORMATTER))
                .append("[")
                .append(Thread.currentThread().getName())
                .append("] ")
                .append(logLevel)
                .append(" ")
                .append(loggerName)
                .append(" - ")
                .append(message)
                .toString();
    }
}
