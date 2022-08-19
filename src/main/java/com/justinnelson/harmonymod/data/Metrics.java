package com.justinnelson.harmonymod.data;

import com.justinnelson.harmonymod.core.Listeners;

import net.dv8tion.jda.api.utils.TimeUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Metrics {
    static final Logger log = LoggerFactory.getLogger(Metrics.class);
    static void info(String message){ log.info(message); }
    static void error(String message){ log.error(message); }
    static void debug(String message){ log.debug(message); }
    static void trace(String message){ log.trace(message); }
    static void warn(String message){ log.warn(message); }

    static int commandCounter = 0;
    public static void categoryCounter(){
        commandCounter++;
        trace("Total commands: " + commandCounter);
    }
    public static void latency(long start, long end, String commandName){
        long latency = TimeUnit.MILLISECONDS.convert(end - start, TimeUnit.NANOSECONDS);
        trace(commandName + " latency: " + latency + " ms.");
    }
    public static void commandCounter(){
        commandCounter++;
    }
}
