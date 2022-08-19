package com.justinnelson.harmonymod.interactions.commands.slash;

import com.justinnelson.harmonymod.interactions.commands.commandprocessors.AbstractSlashCommander;
import com.justinnelson.harmonymod.interactions.commands.CommandCategory;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class SlashMenu extends AbstractSlashCommander {

    public SlashMenu() {
        super(CommandCategory.MODERATION);
    }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        trace(this.getClass().getSimpleName() + " executed.");

    }
}
