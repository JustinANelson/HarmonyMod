package com.justinnelson.harmonymod.interactions.commands.messagecontext;

import com.justinnelson.harmonymod.interactions.commands.CommandCategory;
import com.justinnelson.harmonymod.interactions.commands.commandprocessors.AbstractMessageContextContextCommander;

import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;

public class MessageContextToggleSuccess extends AbstractMessageContextContextCommander {

    public MessageContextToggleSuccess() {
        super(CommandCategory.OWNER);
    }

    @Override
    public void handle(MessageContextInteractionEvent event) {
        logExecution();
    }

    @Override
    public String getName() {
        return null;
    }
}
