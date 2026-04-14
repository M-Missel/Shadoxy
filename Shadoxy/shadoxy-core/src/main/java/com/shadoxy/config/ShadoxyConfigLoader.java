package com.shadoxy.config;

import com.shadoxy.config.sections.AnonymizerConfig;
import com.shadoxy.config.sections.FilterConfig;
import com.shadoxy.config.sections.LoggingConfig;
import com.shadoxy.config.sections.MitmConfig;
import com.shadoxy.config.sections.ServerConfig;
import com.shadoxy.config.sections.UpstreamConfig;
import com.shadoxy.logging.ShadoxyLogger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Implements the loader for the configuration for the proxy server.
 *
 * @author mmissel
 * @version 1.2
 */
public final class ShadoxyConfigLoader {
    private static final ShadoxyLogger logger = ShadoxyLogger.getLogger(ShadoxyConfigLoader.class);

    private static final String LOGGER_START = "Load configuration with file: ";
    private static final String LOGGER_SUCCESSFUL = "Configuration loaded!";

    private final Properties properties;

    private ShadoxyConfigLoader(Properties properties) {
        this.properties = properties;
    }

    /**
     * @return
     * @throws ConfigLoaderException
     */
    public static ShadoxyConfig load() throws ConfigLoaderException {
        return load(DefaultConfig.DEFAULT_APPLICATION_PATH);
    }

    /**
     * @param path
     * @return
     * @throws ConfigLoaderException
     */
    public static ShadoxyConfig load(String path) throws ConfigLoaderException {
        logger.info(LOGGER_START + path);

        Properties props = new Properties();

        try (InputStream inputStream = new FileInputStream(path)) {
            props.load(inputStream);
        } catch (FileNotFoundException e) {
            throw new ConfigLoaderException(e.getMessage() + path);
        } catch (IOException e) {
            throw new ConfigLoaderException(e.getMessage());
        }

        ShadoxyConfig config = new ShadoxyConfigLoader(props).mapToConfig();

        validate(config);

        logger.info(LOGGER_SUCCESSFUL);

        return config;
    }

    private ShadoxyConfig mapToConfig() {
        ShadoxyConfig config = new ShadoxyConfig();

        config.setAnonymizerConfig(mapAnonymizer());
        config.setFilterConfig(mapFilter());
        config.setLoggingConfig(mapLogging());
        config.setMitmConfig(mapMitm());
        config.setServerConfig(mapServer());
        config.setUpstreamConfig(mapUpstream());

        return config;
    }

    private AnonymizerConfig mapAnonymizer() {
        AnonymizerConfig config = new AnonymizerConfig();

        config.setStripHeader(getList(DefaultConfig.STRIP_HEADER));
        config.setRotateUserAgent(getBool(DefaultConfig.ROTATE_USER_AGENT, false));
        config.setBlockCookies(getBool(DefaultConfig.BLOCK_COOKIES, false));

        return config;
    }

    private FilterConfig mapFilter() {
        FilterConfig config = new FilterConfig();

        config.setEnabled(getBool(DefaultConfig.FILTER_ENABLED, false));
        config.setBlockListPath(getString(DefaultConfig.FILTER_BLOCKLIST_PATH, DefaultConfig.DEFAULT_BLOCKLIST_PATH));

        return config;
    }

    private LoggingConfig mapLogging() {
        LoggingConfig config = new LoggingConfig();

        config.setLogLevel(getString(DefaultConfig.LOGGING_LEVEL, DefaultConfig.LOGGING_LEVEL_DEFAULT));

        return config;
    }

    private MitmConfig mapMitm() {
        MitmConfig config = new MitmConfig();

        config.setIsEnabled(getBool(DefaultConfig.MITM_ENABLED, false));
        config.setCaCertPath(getString(DefaultConfig.MITM_CA_CERT_PATH, ""));
        config.setCaKeyPath(getString(DefaultConfig.MITM_CA_KEY_PATH, ""));

        return config;
    }

    private ServerConfig mapServer() {
        ServerConfig config = new ServerConfig();

        config.setServerPort(getInt(DefaultConfig.SERVER_PORT, 8080));
        config.setThreads(getInt(DefaultConfig.SERVER_THREADS, 0));

        return config;
    }

    private UpstreamConfig mapUpstream() {
        UpstreamConfig config = new UpstreamConfig();

        config.setType(getString(DefaultConfig.UPSTREAM_TYPE, "NONE"));
        config.setHost(getString(DefaultConfig.UPSTREAM_HOST, ""));
        config.setPort(getInt(DefaultConfig.UPSTREAM_PORT, 0));

        return config;
    }

    private String getString(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    private int getInt(String key, int defaultValue) {
        try {
            return Integer.parseInt(properties.getProperty(key, String.valueOf(defaultValue)));
        } catch (NumberFormatException e) {
            logger.warn("Ungültiger Wert für " + key + ", verwende Standard: " + defaultValue);
            return defaultValue;
        }
    }

    private boolean getBool(String key, boolean defaultValue) {
        return Boolean.parseBoolean(properties.getProperty(key, String.valueOf(defaultValue)));
    }

    private List<String> getList(String key) {
        String value = properties.getProperty(key, "");

        if (value.isBlank()){
            return new ArrayList<>();
        }

        return Arrays.stream(value.split(","))
                .map(String::trim)
                .filter(st -> !st.isEmpty())
                .collect(Collectors.toList());
    }

    private static void validate(ShadoxyConfig config) throws ConfigLoaderException {
        if (config == null) {
            throw new ConfigLoaderException("Konfigurationsdatei ist leer");
        }
        if (config.getServerConfig().getServerPort() < 1 || config.getServerConfig().getServerPort() > 65535) {
            throw new ConfigLoaderException("Ungültiger Port: " + config.getServerConfig().getServerPort());
        }
        if (config.getUpstreamConfig().getType() == null) {
            throw new ConfigLoaderException("Upstream-Typ darf nicht null sein");
        }
        logger.debug("Validierung erfolgreich — Port: " + config.getServerConfig().getServerPort());
    }
}