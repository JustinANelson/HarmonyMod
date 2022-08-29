package com.justinnelson.harmonymod.data;

import com.justinnelson.harmonymod.core.HarmonyMod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class AppConfig {
    private static final Logger log = LoggerFactory.getLogger(AppConfig.class);

    private static final Properties prop = parseConfig();

    public static final String TOKEN = prop.getProperty("TOKEN");
    public static final String DBURI = prop.getProperty("DBURI");
    public static final String OWNER = prop.getProperty("OWNER");
    public static final String DBNAME = prop.getProperty("DBNAME");
    public static final String SUPPORTSERVER = prop.getProperty("SUPPORTSERVER");

    public static Properties parseConfig()  {
        Properties prop  = new Properties();
        InputStream is;
        try {
            is = HarmonyMod.class.getClassLoader().getResourceAsStream("config.properties");
            if (!Objects.isNull(is)) {
                log.debug("Config file found.");
                prop.load(is);
            } else {
                log.debug("Config file not found. Looking for env.");
                prop.setProperty("TOKEN", System.getenv().get("TOKEN"));
                prop.setProperty("DBURI", System.getenv("DBURI"));
                prop.setProperty("OWNER", System.getenv("OWNER"));
                prop.setProperty("DBNAME", System.getenv("DBNAME"));
                prop.setProperty("SUPPORTSERVER", System.getenv("SUPPORTSERVER"));
            }
        } catch (IOException e) {
            log.debug("Properties not found. Shutting down.");
            System.exit(1);
            e.printStackTrace();
        }
        return prop;
    }
}