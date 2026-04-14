package com.shadoxy.logging;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class LogFileWriter implements LogWriter {
    private String savePath = "logs/shadoxy.log";

    private BufferedWriter writer;

    public LogFileWriter() throws IOException {
        this.writer = new BufferedWriter(new FileWriter(savePath, true));
    }

    public LogFileWriter(String logPath) throws IOException {
        this.savePath = logPath;
        this.writer = new BufferedWriter(new FileWriter(savePath, true));
    }

    /**
     * Defines the write-function.
     *
     * @param message   current message
     * @param logLevel  current log level
     * @param throwable possible cause of the message
     */
    @Override
    public void write(String message, ShadoxyLogLevel logLevel, Throwable throwable) {
        try{
            writer.write(message);
            writer.newLine();

            if(throwable != null){
                stackTraceToString(throwable);
                writer.newLine();
            }

            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Closes the writer
     */
    @Override
    public void close() {
        try{
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String stackTraceToString(Throwable throwable) {
        StringWriter sw = new StringWriter();
        throwable.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}
