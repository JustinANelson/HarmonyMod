package com.justinnelson.harmonymod.commands.commandprocessors;

import com.justinnelson.harmonymod.commands.Commands;
import com.justinnelson.harmonymod.core.HarmonyMod;
import com.justinnelson.harmonymod.data.BotConfig;
import com.justinnelson.harmonymod.data.Metrics;

import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandProcessor {

    // This entire package could benefit from being generified but I don't know how yet.

    public void process(SlashCommandInteractionEvent event){
        if (event.getGuild() == null) {
            event.deferReply(true)
                    .setContent("This bot does not accept commands in Private Messages. Please add it to your guild.")
                    .queue();
            return;
        }

        final var start = System.nanoTime();
        var eventName = "slash" + event.getName();
        Commands.slashCommands.stream()
                .filter(s -> eventName.equals(s.getName()))
                .findAny().ifPresent(e -> e.handle(event));

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

        final var start = System.nanoTime();
        var eventName = event.getName();
        Commands.userContextCommands.stream()
                .filter(s -> eventName.equals(s.getName()))
                .findAny().ifPresent(e -> e.handle(event));

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

        final var start = System.nanoTime();
        var eventName = "messagecontext" + event.getName();
        Commands.messageContextCommands.stream()
                .filter(s -> eventName.equals(s.getName()))
                .findAny().ifPresent(e -> e.handle(event));

        final var end = System.nanoTime();
        Metrics.latency(start, end, event.getName());
        Metrics.commandCounter();
    }

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
}
