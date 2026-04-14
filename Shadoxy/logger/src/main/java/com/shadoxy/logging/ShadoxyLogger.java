package com.shadoxy.logging;

import java.io.IOException;

/**
 * Implements the main logic behind the logger.
 *
 * @author mmiss
 * @version 1.0
 */
public final class ShadoxyLogger {
    private static volatile ShadoxyLogLevel logLevel = ShadoxyLogLevel.INFO;
    private static final LogFormater FORMATER = new LogFormater();
    private static LogWriter logWriter = new ConsoleWriter();

    private final String name;

    private ShadoxyLogger(String name) {
        this.name = name;
    }

    /**
     * Returns the a logger-object.
     *
     * @param thisClass current class, which calls the constructor.
     * @return a new logger object.
     */
    public static ShadoxyLogger getLogger(Class<?> thisClass) {
        return new ShadoxyLogger(thisClass.getSimpleName());
    }

    /**
     * Set the log level to a new value.
     *
     * @param logLevel new log level
     */
    public static void setLogLevel(ShadoxyLogLevel logLevel) {
        ShadoxyLogger.logLevel = logLevel;
    }

    public static void setWriter(String type) throws IOException {
        WriterType writerType = WriterType.valueOf(type);

        switch (writerType){
            case CONSOLE -> logWriter = new ConsoleWriter();
            case FILE -> logWriter = new LogFileWriter();
        }
    }

    /**
     * write the info message.
     *
     * @param message current info message
     */
    public void info(String message) {
        writeLog(ShadoxyLogLevel.INFO, message, null);
    }

    /**
     * write the debug message.
     *
     * @param message current debug message
     */
    public void debug(String message) {
        writeLog(ShadoxyLogLevel.DEBUG, message, null);
    }

    /**
     * write the warn message.
     *
     * @param message current warn message
     */
    public void warn(String message) {
        writeLog(ShadoxyLogLevel.WARN, message, null);
    }

    /**
     * write the error message.
     *
     * @param message current error message
     */
    public void error(String message) {
        writeLog(ShadoxyLogLevel.ERROR, message, null);
    }

    /**
     * write the error message.
     *
     * @param message   current error message
     * @param throwable cause of the error
     */
    public void error(String message, Throwable throwable) {
        writeLog(ShadoxyLogLevel.ERROR, message, throwable);
    }

    private void writeLog(ShadoxyLogLevel level, String message, Throwable throwable) {
        if (!level.isEnabledFor(logLevel)) {
            return;
        }

        String formatted = FORMATER.format(level, name, message);
        logWriter.write(formatted, level, throwable);
    }
}
