package com.shadoxy.config;

public final class DefaultConfig {

    public static final String DEFAULT_APPLICATION_PATH = "configs/application.properties";

    public static final String DEFAULT_BLOCKLIST_PATH = "config/blocklist.txt";

    //------------------------Begin-Key-Values----------------------
    public static final String STRIP_HEADER = "anonymizer.strip-headers";
    public static final String ROTATE_USER_AGENT = "anonymizer.rotate-user-agent";
    public static final String BLOCK_COOKIES = "anonymizer.block-cookies";

    public static final String FILTER_ENABLED = "filter.enabled";
    public static final String FILTER_BLOCKLIST_PATH = "filter.blocklist-path";

    public static final String LOGGING_LEVEL = "logging.level";
    public static final String LOGGING_LEVEL_DEFAULT = "INFO";
    public static final String LOGGING_TYPE = "logging.type";
    public static final String LOGGING_TYPE_DEFAULT = "CONSOLE";

    public static final String MITM_ENABLED = "mitm.enabled";
    public static final String MITM_CA_CERT_PATH = "mitm.ca-cert-path";
    public static final String MITM_CA_KEY_PATH = "mitm.ca-key-path";

    public static final String SERVER_PORT = "server.port";
    public static final String SERVER_THREADS = "server.threads";

    public static final String UPSTREAM_TYPE = "upstream.type";
    public static final String UPSTREAM_HOST = "upstream.host";
    public static final String UPSTREAM_PORT = "upstream.port";
    public static final String UPSTREAM_DEFAULT_VALUE = "NONE";

    //------------------------End-Key-Values----------------------

    public static final String DEFAULT_VALUE = "";
    public static final String DEFAULT_REGEX = ";";

    private DefaultConfig() {
    }
}
