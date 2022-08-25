package com.justinnelson.harmonymod.interactions.commands;

import com.justinnelson.harmonymod.data.HMCollections;
import com.justinnelson.harmonymod.interactions.commands.commandprocessors.AbstractCommand;

import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;

public class MessageContextCommands extends AbstractCommand {

    public void handle(MessageContextInteractionEvent event) {

        String name = event.getName();
        switch (name) {
            case "reportmessage" : reportMessage(event); break;
            default:
        }
    }
    public void reportMessage(MessageContextInteractionEvent event) {

    }
}
