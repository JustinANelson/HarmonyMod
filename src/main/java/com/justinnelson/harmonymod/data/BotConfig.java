package com.justinnelson.harmonymod.data;

public class BotConfig {
    String supportServerID = AppConfig.SUPPORTSERVER;
    String alwaysOnPrefix = "Harmony";
    String defaultPrefix = "=";

    String customPrefix = defaultPrefix;
    boolean standardSuccess = false;

    //Make prefix customizable on a per guild basis
    String[] allPrefixes = {customPrefix, alwaysOnPrefix};

    public String getCustomPrefix() { return customPrefix; }
    public void setCustomPrefix(String customPrefix) { this.customPrefix = customPrefix; }

    public boolean isStandardSuccess() {
        return standardSuccess;
    }
    public void setStandardSuccess(boolean standardSuccess) { this.standardSuccess = standardSuccess; }


}
