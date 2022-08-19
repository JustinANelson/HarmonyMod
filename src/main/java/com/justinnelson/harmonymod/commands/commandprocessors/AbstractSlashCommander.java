package com.justinnelson.harmonymod.commands.commandprocessors;

import com.justinnelson.harmonymod.commands.CommandCategory;
import com.justinnelson.harmonymod.commands.Commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public abstract class AbstractSlashCommander implements SlashCommander {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    public String name = this.getClass().getSimpleName().toLowerCase(Locale.ROOT);
    public boolean guildOnly;
    public final CommandCategory thisCategory;

    public AbstractSlashCommander(CommandCategory category) {
        thisCategory = category;
        this.guildOnly = false;
        Commands.registerSlashCommand(this);
    }
    public AbstractSlashCommander(CommandCategory category, boolean guildOnly) {
        thisCategory = category;
        this.guildOnly = false;
        Commands.registerSlashCommand(this);
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

    public boolean guildOnly() { return false; }
    public void logExecution() {
        trace(this.getClass().getSimpleName() + " executed.");
    }

}


