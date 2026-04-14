package com.shadoxy.logging;

import java.io.PrintStream;

/**
 * Implementation of the Console-Writer of the logger. It is usead for debuging purposes.
 *
 * @author mmiss
 * @version 1.1
 */
public class ConsoleWriter implements LogWriter {

    @Override
    public void write(String message, ShadoxyLogLevel logLevel, Throwable throwable) {
        PrintStream target;

        if (logLevel == ShadoxyLogLevel.ERROR || logLevel == ShadoxyLogLevel.WARN) {
            target = System.err;
        } else {
            target = System.out;
        }

        synchronized (target) {
            target.println(message);

            if (throwable != null) {
                throwable.printStackTrace(target);
            }
        }
    }

    @Override
    public void close() {

    }
}
