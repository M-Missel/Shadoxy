package com.shadoxy.config.sections;

public class LoggingConfig {
    private String logLevel;
    private String logType;
    private Integer maxSize;
    private Integer maxDuration;

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getLogLevel() {
        return this.logLevel;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getLogType() {
        return this.logType;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    public Integer getMaxSize() {
        return this.maxSize;
    }

    public void setMaxDuration(Integer maxDuration) {
        this.maxDuration = maxDuration;
    }

    public Integer getMaxDuration() {
        return this.maxDuration;
    }
}