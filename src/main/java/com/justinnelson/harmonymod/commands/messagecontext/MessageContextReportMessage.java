package com.justinnelson.harmonymod.commands.messagecontext;

import com.justinnelson.harmonymod.commands.CommandCategory;
import com.justinnelson.harmonymod.commands.commandprocessors.AbstractMessageContextContextCommander;

import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;

public class MessageContextReportMessage extends AbstractMessageContextContextCommander {

    public MessageContextReportMessage() {
        super(CommandCategory.UTILS);
    }

    @Override
    public void handle(MessageContextInteractionEvent event) {
        logExecution();
    }
}
