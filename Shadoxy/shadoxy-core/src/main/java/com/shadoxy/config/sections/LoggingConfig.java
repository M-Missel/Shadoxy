package com.shadoxy.config.sections;

public class LoggingConfig {
    private String logLevel;
    private String logType;

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getLogLevel(){
        return this.logLevel;
    }

    public void setLogType(String logType){
        this.logType = logType;
    }

    public String getLogType(){
        return this.logType;
    }
}
