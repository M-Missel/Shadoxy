package com.shadoxy.config;

/**
 * Implements the needed own exception.
 *
 * @author mmiss
 * @version 1.0
 */
public class ConfigLoaderException extends Exception {
    /**
     * Throws a new exception.
     *
     * @param message error-message
     */
    public ConfigLoaderException(String message) {
        super(message);
    }

    /**
     * Throws a new exception
     *
     * @param message error-message
     * @param cause   cause of the error
     */
    public ConfigLoaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
