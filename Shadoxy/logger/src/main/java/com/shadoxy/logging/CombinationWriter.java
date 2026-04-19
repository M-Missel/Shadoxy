package com.shadoxy.logging;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CombinationWriter implements LogWriter {
    private final List<LogWriter> logWriter;

    public CombinationWriter(LogWriter consoleWriter, LogWriter fileWriter){
        logWriter = new ArrayList<>();
        logWriter.add(consoleWriter);
        logWriter.add(fileWriter);
    }
    /**
     * Defines the write-function.
     *
     * @param message   current message
     * @param logLevel  current log level
     * @param throwable possible cause of the message
     */
    @Override
    public void write(String message, ShadoxyLogLevel logLevel, Throwable throwable) throws IOException {
        for(LogWriter writer : logWriter){
            writer.write(message, logLevel, throwable);
        }
    }

    /**
     * Closes the writer
     */
    @Override
    public void close() {
        for(LogWriter writer : logWriter){
            writer.close();
        }
    }
}
