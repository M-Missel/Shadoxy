package com.shadoxy.config.sections;

public class ServerConfig {
    private int serverPort;
    private int threads;

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public int getServerPort() {
        return this.serverPort;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    public int getThreads() {
        return this.threads;
    }
}
