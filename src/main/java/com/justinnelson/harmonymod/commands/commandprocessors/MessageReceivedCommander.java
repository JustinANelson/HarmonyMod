package com.justinnelson.harmonymod.commands.commandprocessors;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface MessageReceivedCommander {
    void handle(MessageReceivedEvent event);
    void read();
    String getName();
}
