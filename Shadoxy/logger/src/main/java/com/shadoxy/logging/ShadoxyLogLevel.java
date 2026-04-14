package com.shadoxy.logging;

/**
 * Defines all the log levels.
 *
 * @author mmiss
 * @version 1.0
 */
public enum ShadoxyLogLevel {
    /**
     * Defines the trace log level.
     */
    TRACE,
    /**
     * Defines the debug log level.
     */
    DEBUG,
    /**
     * Defines the default log level.
     * It is used of the normal behavior and importent steps in the usage of the application.
     */
    INFO,
    /**
     * Defines errors of the stage 1. Defined as non fatal failures of the system
     */
    WARN,
    /**
     * Defines a fatal failure of the system.
     * It is used to log important failures in the core, common and filter-plugin.
     */
    ERROR;

    /**
     * Checks if the current set log level is the same with the parsed.
     *
     * @param minLevel current min-log level
     * @return true if the parsed min level is greater or equal the set log level.
     */
    public boolean isEnabledFor(Enum<ShadoxyLogLevel> minLevel) {
        return this.ordinal() >= minLevel.ordinal();
    }
}
