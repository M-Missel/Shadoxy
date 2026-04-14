package com.shadoxy;

import com.shadoxy.config.ConfigLoaderException;
import com.shadoxy.config.DefaultConfig;
import com.shadoxy.config.ShadoxyConfig;
import com.shadoxy.config.ShadoxyConfigLoader;
import com.shadoxy.config.sections.LoggingConfig;
import com.shadoxy.logging.ShadoxyLogLevel;
import com.shadoxy.logging.ShadoxyLogger;
import com.shadoxy.server.ShadoxyServer;

import java.io.IOException;

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
     *
     * @param args No arguments are parsed.
     * @throws InterruptedException l
     * @throws IOException          ö
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        logger.info(START_LOGGER);

        ShadoxyConfig config;

        try {
            config = ShadoxyConfigLoader.load();
        } catch (ConfigLoaderException e) {
            logger.error("Failed to load the config", e);
            return;
        }

        configureLogging(config.getLoggingConfig());

        logger.info(CONFIG_LOADED + config.getServerConfig().getServerPort());

        ShadoxyServer server = new ShadoxyServer(config);
        server.start();
    }

    private static void configureLogging(LoggingConfig config) throws IOException {
        ShadoxyLogger.setLogLevel(
                ShadoxyLogLevel.valueOf(config.getLogLevel().toUpperCase())
        );

        String logType = config.getLogType();

        if (!logType.equals(DefaultConfig.LOGGING_TYPE)) {
            ShadoxyLogger.setWriter(logType);
        }
    }
}