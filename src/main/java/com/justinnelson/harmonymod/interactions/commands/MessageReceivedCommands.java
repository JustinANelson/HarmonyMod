package com.justinnelson.harmonymod.interactions.commands;

import com.justinnelson.harmonymod.interactions.commands.commandprocessors.AbstractCommand;
import com.justinnelson.harmonymod.interactions.commands.customcommands.MessageReceivedInteractionEvent;

public class MessageReceivedCommands extends AbstractCommand {

    public void handle(MessageReceivedInteractionEvent event) {

        String name = event.getName();
        switch (name) {
            case "placeholder" : placeholder(event); break;
            case "echo": echo(event); break;
            default:
        }
    }
    public void placeholder(MessageReceivedInteractionEvent event) {

    }
    public void echo(MessageReceivedInteractionEvent event) {
        System.out.println("echo command received.");
    }
}
