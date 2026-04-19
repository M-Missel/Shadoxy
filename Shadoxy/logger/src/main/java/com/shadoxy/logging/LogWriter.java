package com.shadoxy.logging;

import java.io.IOException;

/**
 * Implements all the needed methods for the next steps.
 *
 * @author mmiss
 * @version 1.0
 */
public interface LogWriter {
    /**
     * Defines the write-function.
     *
     * @param message   current message
     * @param logLevel  current log level
     * @param throwable possible cause of the message
     */
    void write(String message, ShadoxyLogLevel logLevel, Throwable throwable) throws IOException;

    /**
     * Closes the writer
     */
    void close();
}
