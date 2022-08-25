package com.justinnelson.harmonymod.interactions.commands.commandprocessors;

import com.justinnelson.harmonymod.data.HMCollections;
import com.justinnelson.harmonymod.interactions.commands.Commands;
import com.justinnelson.harmonymod.core.HarmonyMod;
import com.justinnelson.harmonymod.data.Metrics;
import com.justinnelson.harmonymod.interactions.commands.MessageContextCommands;
import com.justinnelson.harmonymod.interactions.commands.SlashCommands;
import com.justinnelson.harmonymod.interactions.commands.UserContextCommands;

import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandProcessor {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    protected void info(String message){ log.info(message); }
    protected void error(String message){ log.error(message); }
    protected void debug(String message){ log.debug(message); }
    protected void trace(String message){ log.trace(message); }
    protected void warn(String message){ log.warn(message); }

    SlashCommands slashCommands;
    MessageContextCommands messageContextCommands;
    UserContextCommands userContextCommands;

    public CommandProcessor() {
        Commands.create();
        slashCommands = new SlashCommands();
        messageContextCommands = new MessageContextCommands();
        userContextCommands = new UserContextCommands();
    }
    public void process(SlashCommandInteractionEvent event){

        if (event.getGuild() == null) {
            event.deferReply(true)
                    .setContent("This bot does not accept commands in Private Messages. Please add it to your guild.")
                    .queue();
            return;
        }

        if (HMCollections.slashCommands.stream().map(e -> e.getName().contains(event.getName()))
                .findAny()
                .isPresent()) {
            logExecution(event.getName());
        }
        else { return; }

        final var start = System.nanoTime();

        slashCommands.handle(event);

        final var end = System.nanoTime();
        Metrics.latency(start, end, event.getName());
        Metrics.commandCounter();
    }

    public void process(UserContextInteractionEvent event) {

        if (event.getGuild() == null) {
            event.deferReply(true)
                    .setContent("This bot does not accept commands in Private Messages. Please add it to your guild.")
                    .queue();
            return;
        }

        if (HMCollections.userContextCommands.stream().map(e -> e.getName().contains(event.getName()))
                .findAny()
                .isPresent()) {
            logExecution(event.getName());
        }
        else { return; }

        final var start = System.nanoTime();

        userContextCommands.handle(event);

        final var end = System.nanoTime();
        Metrics.latency(start, end, event.getName());
        Metrics.commandCounter();

    }

    public void process(MessageContextInteractionEvent event) {

        if (event.getGuild() == null) {
            event.deferReply(true)
                    .setContent("This bot does not accept commands in Private Messages. Please add it to your guild.")
                    .queue();
            return;
        }

        if (HMCollections.messageContextCommands.stream().map(e -> e.getName().contains(event.getName()))
                .findAny()
                .isPresent()) {
            logExecution(event.getName());
        }
        else { return; }

        final var start = System.nanoTime();

        messageContextCommands.handle(event);

        final var end = System.nanoTime();
        Metrics.latency(start, end, event.getName());
        Metrics.commandCounter();

    }

    /*
    public void process(MessageReceivedEvent event) {
        if (event.getGuild() == null) {
            event.getChannel()
                    .sendMessage("This bot does not accept commands in Private Messages. Please add it to your guild.")
                    .queue();
            return;
        }
        String content = event.getMessage().getContentRaw();
        String[] strings = content.split("\\s+");
        String command = strings[0].substring(HarmonyMod.botConfig.getCustomPrefix().length());

        final var start = System.nanoTime();
        var eventName = "messagereceived" + command;
        Commands.messageReceivedCommands.stream()
                .filter(s -> eventName.equals(s.getName()))
                .findAny().ifPresent(e -> e.handle(event));

        final var end = System.nanoTime();
        Metrics.latency(start, end, strings[0]);
        Metrics.commandCounter();
    }
    */

    public void logExecution(String name) {
        trace(name + " executed.");
    }
}
