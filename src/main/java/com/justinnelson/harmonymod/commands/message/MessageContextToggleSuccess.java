package com.justinnelson.harmonymod.commands.message;

import com.justinnelson.harmonymod.commands.CommandCategory;
import com.justinnelson.harmonymod.commands.commandprocessors.AbstractMessageContextContextCommander;

import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;

public class MessageContextToggleSuccess extends AbstractMessageContextContextCommander {

    public MessageContextToggleSuccess() {
        super(CommandCategory.OWNER);
    }

    @Override
    public void handle(MessageContextInteractionEvent event) {

    }

    @Override
    public String getName() {
        return null;
    }
}
