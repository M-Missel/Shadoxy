package com.shadoxy.logging;

public class ConfigLoaderException extends Exception {
    public ConfigLoaderException(String message) {
        super(message);
    }

    public ConfigLoaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
