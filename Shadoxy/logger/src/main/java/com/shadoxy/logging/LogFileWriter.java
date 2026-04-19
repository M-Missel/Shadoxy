package com.shadoxy.logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.ZoneId;

public class LogFileWriter implements LogWriter {
    private String savePath = "logs/shadoxy.log";
    private final long maxSize;

    private BufferedWriter writer;

    public LogFileWriter() throws IOException {
        this.writer = new BufferedWriter(new FileWriter(savePath, true));
        maxSize = 10;
    }

    public LogFileWriter(String logPath, int maxSize) throws IOException {
        this.savePath = logPath;
        this.writer = new BufferedWriter(new FileWriter(savePath, true));
        this.maxSize = maxSize;
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
        File currentFile = Path.of(savePath).normalize().toFile();
        rotateFile(currentFile);

        try {
            writer.write(message);
            writer.newLine();

            if (throwable != null) {
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
        try {
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

    private void rotateFile(File logFile) throws IOException {
        Path path = Path.of(savePath);
        BasicFileAttributes basicFileAttributes = Files.readAttributes(path, BasicFileAttributes.class);

        LocalDate localDate = basicFileAttributes.creationTime()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        long sizeInMB = Files.size(logFile.toPath()) / (1024 * 1024);

        if (!localDate.isEqual(LocalDate.now()) || sizeInMB >= maxSize) {
            writer.close();

            File rotated = new File("logs/shadoxy-" + localDate + ".log");
            if (!logFile.renameTo(rotated)) {
                throw new IOException("Failed to rotate log file to: " + rotated.getName());
            }

            writer = new BufferedWriter(new FileWriter(savePath, true));
        }
    }
}
