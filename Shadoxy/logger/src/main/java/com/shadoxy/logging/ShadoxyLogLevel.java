package com.shadoxy.logging;

public enum ShadoxyLogLevel {
    TRACE,
    DEBUG,
    INFO,
    WARN,
    ERROR;

    public boolean isEnabledFor(Enum<ShadoxyLogLevel> minLevel) {
        return this.ordinal() >= minLevel.ordinal();
    }
}
