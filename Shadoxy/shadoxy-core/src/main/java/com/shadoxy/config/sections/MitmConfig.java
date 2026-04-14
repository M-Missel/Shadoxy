package com.shadoxy.config.sections;

public class MitmConfig {
    private boolean isEnabled;
    private String caCertPath;
    private String caKeyPath;

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public boolean getIsEnabled(){
        return this.isEnabled;
    }

    public String getCaCertPath() {
        return caCertPath;
    }

    public void setCaCertPath(String caCertPath){
        this.caCertPath = caCertPath;
    }

    public String getCaKeyPath() {
        return caKeyPath;
    }

    public void setCaKeyPath(String caKeyPath) {
        this.caKeyPath = caKeyPath;
    }
}
