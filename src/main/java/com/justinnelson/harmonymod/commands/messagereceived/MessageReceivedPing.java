package com.justinnelson.harmonymod.commands.messagereceived;

import com.justinnelson.harmonymod.commands.CommandCategory;
import com.justinnelson.harmonymod.commands.commandprocessors.AbstractMessageReceivedCommander;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MessageReceivedPing extends AbstractMessageReceivedCommander {
    public MessageReceivedPing() {
        super(CommandCategory.UTILS);
    }

    @Override
    public void handle(MessageReceivedEvent event) {
        logExecution();
        event.getChannel().sendMessage("ping").queue();
    }
}
