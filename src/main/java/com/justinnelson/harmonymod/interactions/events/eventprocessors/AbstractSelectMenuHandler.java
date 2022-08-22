package com.justinnelson.harmonymod.interactions.events.eventprocessors;

import com.justinnelson.harmonymod.interactions.events.Events;

import net.dv8tion.jda.api.interactions.InteractionHook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public abstract class AbstractSelectMenuHandler implements SelectMenuHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    public String name = this.getClass().getSimpleName().toLowerCase(Locale.ROOT);
    public InteractionHook hook;

    public AbstractSelectMenuHandler() {
        Events.registerSelectMenus(this);
    }

    protected void info(String message){ log.info(message); }
    protected void error(String message){ log.error(message); }
    protected void debug(String message){ log.debug(message); }
    protected void trace(String message){ log.trace(message); }
    protected void warn(String message){ log.warn(message); }

    public void read(){
        System.out.println(this.getName());
    }
    public String getName(){ return this.name; }
    public void logExecution() {
        trace(this.getClass().getSimpleName() + " executed.");
    }

}
