package com.shadoxy;

import com.shadoxy.logging.ShadoxyLogger;

/**
 * Entry point for the proxy server.
 *
 * @author mmiss
 * @version 1.0
 */
public final class ShadoxyApplication {
    private static final String START_LOGGER = "Starting Shadoxy";
    private static final String CONFIG_LOADED = "Config loaded - Port: ";
    private static final ShadoxyLogger logger = ShadoxyLogger.getLogger(ShadoxyApplication.class);

    private ShadoxyApplication() {
    }

    /**
     * Entry point of the application.
     * @param args No arguments are parsed.
     */
    public static void main(String[] args) {
        logger.info(START_LOGGER);

    }
}
