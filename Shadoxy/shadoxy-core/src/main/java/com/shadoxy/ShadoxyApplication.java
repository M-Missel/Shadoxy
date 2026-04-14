package com.shadoxy;

import com.shadoxy.config.ConfigLoaderException;
import com.shadoxy.config.ShadoxyConfig;
import com.shadoxy.config.ShadoxyConfigLoader;
import com.shadoxy.logging.ShadoxyLogLevel;
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

        ShadoxyConfig config;

        try{
            config = ShadoxyConfigLoader.load();
        } catch (ConfigLoaderException e) {
            logger.error("Failed to load the config", e);
            return;
        }

        ShadoxyLogger.setLogLevel(
                ShadoxyLogLevel.valueOf(config.getLoggingConfig().getLogLevel().toUpperCase())
        );

        logger.info(CONFIG_LOADED + config.getServerConfig().getServerPort());
    }
}
