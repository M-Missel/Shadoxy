package com.shadoxy.logging;

import java.io.PrintStream;

public class ConsoleWriter implements LogWriter {

    @Override
    public void write(String message, ShadoxyLogLevel logLevel, Throwable throwable) {
        PrintStream target;

        if (logLevel == ShadoxyLogLevel.ERROR || logLevel == ShadoxyLogLevel.WARN) {
            target = System.err;
        } else {
            target = System.out;
        }

        target.println(message);

        if (throwable != null) {
            throwable.printStackTrace(target);
        }
    }

    @Override
    public void close() {

    }
}
