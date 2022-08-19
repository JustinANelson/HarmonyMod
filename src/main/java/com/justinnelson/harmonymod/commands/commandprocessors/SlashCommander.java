package com.justinnelson.harmonymod.commands.commandprocessors;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public interface SlashCommander {
    void handle(SlashCommandInteractionEvent event);
    void read();
    String getName();
}
