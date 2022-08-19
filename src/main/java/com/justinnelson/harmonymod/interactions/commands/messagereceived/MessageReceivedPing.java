package com.justinnelson.harmonymod.interactions.commands.messagereceived;

import com.justinnelson.harmonymod.interactions.commands.CommandCategory;
import com.justinnelson.harmonymod.interactions.commands.commandprocessors.AbstractMessageReceivedCommander;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MessageReceivedPing extends AbstractMessageReceivedCommander {
    public MessageReceivedPing() {
        super(CommandCategory.UTILS);
    }

    @Override
    public void handle(MessageReceivedEvent event) {
        logExecution();
        long time = System.currentTimeMillis();
        event.getChannel().sendMessage("ping")
                .queue(e -> e.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue());
    }
}
