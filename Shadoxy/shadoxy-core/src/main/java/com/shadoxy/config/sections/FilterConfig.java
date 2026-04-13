package com.shadoxy.config.sections;

import com.shadoxy.config.DefaultConfig;

public class FilterConfig {
    private boolean enabled;
    private String blockListPath = DefaultConfig.DEFAULT_BLOCKLIST_PATH;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getBlockListPath() {
        return blockListPath;
    }

    public void setBlockListPath(String blockListPath) {
        this.blockListPath = blockListPath;
    }
}
