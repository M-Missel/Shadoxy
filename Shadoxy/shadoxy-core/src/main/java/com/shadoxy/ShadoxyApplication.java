package com.shadoxy;

import com.shadoxy.logging.ShadoxyLogger;

public final class ShadoxyApplication {
    private static final String START_LOGGER = "Starting Shadoxy";
    private static final String CONFIG_LOADED = "Config loaded - Port: ";
    private static final ShadoxyLogger logger = ShadoxyLogger.getLogger(ShadoxyApplication.class);

    private ShadoxyApplication() {
    }

    public static void main(String[] args) {
        logger.info(START_LOGGER);

    }
}
