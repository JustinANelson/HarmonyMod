package com.justinnelson.harmonymod.data;

import com.justinnelson.harmonymod.AppConfig;

import java.util.Random;

public class BotConfig {
    String supportServerID = AppConfig.SUPPORTSERVER;
    String alwaysOnPrefix = "Harmony";
    String defaultPrefix = "=";
    public Random random = new Random();

    String customPrefix = defaultPrefix;
    boolean standardSuccess = false;

    //Make prefix customizable on a per guild basis
    String[] allPrefixes = {customPrefix, alwaysOnPrefix};

    public String getSupportServerID() {return supportServerID;}
    public String getAlwaysOnPrefix() {return alwaysOnPrefix;}
    public String getDefaultPrefix() {return defaultPrefix;}
    public String getCustomPrefix() {return customPrefix;}
    public boolean getStandardSuccess() {return standardSuccess;}
    public String[] getAllPrefixes() {return allPrefixes;}

    public void setSupportServerID(String supportServerID) {this.supportServerID = supportServerID;}
    public void setAlwaysOnPrefix(String alwaysOnPrefix) {this.alwaysOnPrefix = alwaysOnPrefix;}
    public void setDefaultPrefix(String defaultPrefix) {this.defaultPrefix = defaultPrefix;}
    public void setCustomPrefix(String customPrefix) {this.customPrefix = customPrefix;}
    public void setStandardSuccess(boolean standardSuccess) {this.standardSuccess = standardSuccess;}
    public void setAllPrefixes(String[] allPrefixes) {this.allPrefixes = allPrefixes;}
}
