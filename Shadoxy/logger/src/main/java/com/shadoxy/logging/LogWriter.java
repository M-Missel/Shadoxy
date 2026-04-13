package com.shadoxy.logging;

public interface LogWriter {
    void write(String message, ShadoxyLogLevel logLevel, Throwable throwable);

    void close();
}
