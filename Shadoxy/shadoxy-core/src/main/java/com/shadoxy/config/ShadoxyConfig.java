package com.shadoxy.config;

import com.shadoxy.config.sections.AnonymizerConfig;
import com.shadoxy.config.sections.FilterConfig;
import com.shadoxy.config.sections.LoggingConfig;
import com.shadoxy.config.sections.MitmConfig;
import com.shadoxy.config.sections.ServerConfig;
import com.shadoxy.config.sections.UpstreamConfig;

public class ShadoxyConfig {
    private AnonymizerConfig anonymizerConfig = new AnonymizerConfig();
    private FilterConfig filterConfig = new FilterConfig();
    private LoggingConfig loggingConfig = new LoggingConfig();
    private MitmConfig mitmConfig = new MitmConfig();
    private ServerConfig serverConfig = new ServerConfig();
    private UpstreamConfig upstreamConfig = new UpstreamConfig();

    public AnonymizerConfig getAnonymizerConfig() {
        return anonymizerConfig;
    }

    public void setAnonymizerConfig(AnonymizerConfig anonymizerConfig) {
        this.anonymizerConfig = anonymizerConfig;
    }

    public FilterConfig getFilterConfig() {
        return filterConfig;
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public LoggingConfig getLoggingConfig() {
        return loggingConfig;
    }

    public void setLoggingConfig(LoggingConfig loggingConfig) {
        this.loggingConfig = loggingConfig;
    }

    public MitmConfig getMitmConfig() {
        return mitmConfig;
    }

    public void setMitmConfig(MitmConfig mitmConfig) {
        this.mitmConfig = mitmConfig;
    }

    public ServerConfig getServerConfig() {
        return serverConfig;
    }

    public void setServerConfig(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

    public UpstreamConfig getUpstreamConfig() {
        return upstreamConfig;
    }

    public void setUpstreamConfig(UpstreamConfig upstreamConfig) {
        this.upstreamConfig = upstreamConfig;
    }
}
