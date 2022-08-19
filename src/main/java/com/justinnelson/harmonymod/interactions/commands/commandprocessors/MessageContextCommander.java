package com.justinnelson.harmonymod.interactions.commands.commandprocessors;

import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;

public interface MessageContextCommander {
    void handle(MessageContextInteractionEvent event);
    void read();
    String getName();
}
