package com.justinnelson.harmonymod.commands.slash;

import com.justinnelson.harmonymod.commands.commandprocessors.AbstractSlashCommander;
import com.justinnelson.harmonymod.commands.CommandCategory;

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
