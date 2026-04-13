package com.shadoxy.config;

import com.shadoxy.config.sections.AnonymizerConfig;
import com.shadoxy.config.sections.FilterConfig;
import com.shadoxy.logging.ConfigLoaderException;
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

public final class ShadoxyConfigLoader {
    private static final ShadoxyLogger logger = ShadoxyLogger.getLogger(ShadoxyConfigLoader.class);

    private static final String LOGGER_START = "Load configuration with file: ";
    private static final String LOGGER_SUCCESSFUL = "Configuration loaded!";

    private final Properties properties;

    private ShadoxyConfigLoader(Properties properties) {
        this.properties = properties;
    }

    public static ShadoxyConfig load() throws ConfigLoaderException {
        return load(DefaultConfig.DEFAULT_APPLICATION_PATH);
    }

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

        return new ShadoxyConfig();
    }

    private ShadoxyConfig mapToConfig(Properties properties) {
        ShadoxyConfig config = new ShadoxyConfig();
        config.setAnonymizerConfig(mapAnonymizer());
    }

    private AnonymizerConfig mapAnonymizer() {
        AnonymizerConfig config = new AnonymizerConfig();

        config.setStripHeader(getList("anonymizer.strip-headers"));
        config.setRotateUserAgent(getBool("anonymizer.rotate-user-agent", false));
        config.setBlockCookies(getBool("anonymizer.block-cookies", false));

        return config;
    }

    private FilterConfig mapFilter(){
        FilterConfig config = new FilterConfig();

        config.setEnabled(getBool("filter.enabled", false));
        config.setBlockListPath(getString("filter.blocklist-path", DefaultConfig.DEFAULT_BLOCKLIST_PATH));

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
        if (value.isBlank()) return new ArrayList<>();
        return Arrays.stream(value.split(","))
                .map(String::trim)
                .filter(st -> !st.isEmpty())
                .collect(Collectors.toList());
    }

    private static void validate(ShadoxyConfig config) {
        if (config == null) {
            throw new RuntimeException("Konfigurationsdatei ist leer");
        }
        if (config.getServer().getPort() < 1 || config.getServer().getPort() > 65535) {
            throw new RuntimeException("Ungültiger Port: " + config.getServer().getPort());
        }
        if (config.getUpstream().getType() == null) {
            throw new RuntimeException("Upstream-Typ darf nicht null sein");
        }
        logger.debug("Validierung erfolgreich — Port: " + config.getServer().getPort());
    }
}
