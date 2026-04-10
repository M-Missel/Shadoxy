package com.shadoxy.config;

import com.shadoxy.logging.ShadoxyLogger;

public class ShadoxyConfigLoader {
    private static final ShadoxyLogger logger = ShadoxyLogger.getLogger(ShadoxyConfigLoader.class);
    private static final String DEFAULT_PATH = "configs/application.yaml";

    private static final String LOGGER_START = "";

    public static ShadoxyConfig load() {
        return load(DEFAULT_PATH);
    }

    public static ShadoxyConfig load(String path) {
        logger.info(LOGGER_START);

        return new ShadoxyConfig();
    }
}
