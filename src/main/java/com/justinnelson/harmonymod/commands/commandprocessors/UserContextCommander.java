package com.justinnelson.harmonymod.commands.commandprocessors;

import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;

public interface UserContextCommander {
    void handle(UserContextInteractionEvent event);
    void read();
    String getName();
}
