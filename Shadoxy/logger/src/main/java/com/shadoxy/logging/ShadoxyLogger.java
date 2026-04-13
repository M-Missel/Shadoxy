package com.shadoxy.logging;

public final class ShadoxyLogger {
    private static ShadoxyLogLevel logLevel = ShadoxyLogLevel.INFO;
    private static final LogFormater FORMATER = new LogFormater();
    private static final LogWriter WRITER = new ConsoleWriter();

    private final String name;

    private ShadoxyLogger(String name) {
        this.name = name;
    }

    public static ShadoxyLogger getLogger(Class<?> thisClass) {
        return new ShadoxyLogger(thisClass.getSimpleName());
    }

    public static void setLogLevel(ShadoxyLogLevel logLevel) {
        ShadoxyLogger.logLevel = logLevel;
    }

    public void info(String message) {
        writeLog(ShadoxyLogLevel.INFO, message, null);
    }

    public void debug(String message) {
        writeLog(ShadoxyLogLevel.DEBUG, message, null);
    }

    public void warn(String message) {
        writeLog(ShadoxyLogLevel.WARN, message, null);
    }

    public void error(String message) {
        writeLog(ShadoxyLogLevel.ERROR, message, null);
    }

    public void error(String message, Throwable throwable) {
        writeLog(ShadoxyLogLevel.ERROR, message, throwable);
    }

    private void writeLog(ShadoxyLogLevel level, String message, Throwable throwable) {
        if (!level.isEnabledFor(logLevel)) {
            return;
        }

        String formatted = FORMATER.format(level, name, message);
        WRITER.write(formatted, level, throwable);
    }
}
