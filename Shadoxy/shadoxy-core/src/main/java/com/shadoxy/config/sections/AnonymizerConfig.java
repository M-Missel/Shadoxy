package com.shadoxy.config.sections;

import java.util.ArrayList;
import java.util.List;

public class AnonymizerConfig {
    private List<String> stripHeader = new ArrayList<>();
    private boolean rotateUserAgent = false;
    private boolean blockCookies = false;

    public List<String> getStripHeader() {
        return List.copyOf(stripHeader);
    }

    public void setStripHeader(List<String> stripHeader) {
        this.stripHeader = List.copyOf(stripHeader);
    }

    public boolean isRotateUserAgent() {
        return rotateUserAgent;
    }

    public void setRotateUserAgent(boolean rotateUserAgent) {
        this.rotateUserAgent = rotateUserAgent;
    }

    public boolean isBlockCookies() {
        return blockCookies;
    }

    public void setBlockCookies(boolean blockCookies) {
        this.blockCookies = blockCookies;
    }
}
