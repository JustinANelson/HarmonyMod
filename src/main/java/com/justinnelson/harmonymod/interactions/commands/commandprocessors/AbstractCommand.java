package com.justinnelson.harmonymod.interactions.commands.commandprocessors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractCommand {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    protected void info(String message){ log.info(message); }
    protected void error(String message){ log.error(message); }
    protected void debug(String message){ log.debug(message); }
    protected void trace(String message){ log.trace(message); }
    protected void warn(String message){ log.warn(message); }

}
