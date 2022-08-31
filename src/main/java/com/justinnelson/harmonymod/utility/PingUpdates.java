package com.justinnelson.harmonymod.utility;

import static com.justinnelson.harmonymod.HarmonyMod.jda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PingUpdates implements Runnable{
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    void info(String message){ log.info(message); }
    void error(String message){ log.error(message); }
    void debug(String message){ log.debug(message); }
    void trace(String message){ log.trace(message); }
    void warn(String message){ log.warn(message); }

    @Override
    public void run() {
        String message;
        while (true) {
            message = null;
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long ping = jda.getGatewayPing();
            message = "Gateway ping is " + ping;
            trace(message);
        }
    }
}
