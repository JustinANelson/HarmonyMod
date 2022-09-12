package com.justinnelson.harmonymod.modules.interactions.commands.commandprocessors;

import com.justinnelson.harmonymod.HarmonyMod;
import com.justinnelson.harmonymod.data.HMCollections;
import com.justinnelson.harmonymod.data.Metrics;
import com.justinnelson.harmonymod.modules.interactions.commands.Commands;
import com.justinnelson.harmonymod.modules.interactions.commands.MessageContextCommands;
import com.justinnelson.harmonymod.modules.interactions.commands.MessageReceivedCommands;
import com.justinnelson.harmonymod.modules.interactions.commands.SlashCommands;
import com.justinnelson.harmonymod.modules.interactions.commands.UserContextCommands;
import com.justinnelson.harmonymod.modules.interactions.commands.customcommands.MessageReceivedInteractionEvent;
import com.justinnelson.harmonymod.utility.Util;

import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;

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
    MessageReceivedCommands messageReceivedCommands;
    UserContextCommands userContextCommands;

    public CommandProcessor() {
        Commands.create();
        slashCommands = new SlashCommands();
        messageContextCommands = new MessageContextCommands();
        messageReceivedCommands = new MessageReceivedCommands();
        userContextCommands = new UserContextCommands();
    }
    public void process(SlashCommandInteractionEvent event){

        if (event.getGuild() == null) {
            event.deferReply(true)
                    .setContent("This bot does not accept commands in Private Messages. Please add it to your guild.")
                    .queue();
            return;
        }

        if (HMCollections.slashCommands.stream().map(command -> command.getName().contains(event.getName()))
                .findAny()
                .isPresent()) {
            Util.logExecution(event.getMember().getEffectiveName(), event.getName());
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
            Util.logExecution(event.getMember().getEffectiveName(), event.getName());
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
            Util.logExecution(event.getMember().getEffectiveName(), event.getName());
        }
        else { return; }

        final var start = System.nanoTime();

        messageContextCommands.handle(event);

        final var end = System.nanoTime();
        Metrics.latency(start, end, event.getName());
        Metrics.commandCounter();

    }
    public void process(MessageReceivedInteractionEvent event) {
        if (event.getGuild() == null) {
            return;
        }
        String content = event.getMessage().getContentRaw();
        String[] strings = content.split("\\s+");
        String command = strings[0].substring(HarmonyMod.botConfig.getCustomPrefix().length());

        final var start = System.nanoTime();

        messageReceivedCommands.handle(event);

        final var end = System.nanoTime();
        Metrics.latency(start, end, strings[0]);
        Metrics.commandCounter();
    }
}
