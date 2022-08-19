package com.justinnelson.harmonymod.commands.commandprocessors;

import com.justinnelson.harmonymod.commands.Commands;
import com.justinnelson.harmonymod.data.Metrics;

import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;

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
        System.out.println(event.getInteraction());
        System.out.println(event.getChannel());
        if (event.getGuild() == null) {
            event.deferReply(true)
                    .setContent("This bot does not accept commands in Private Messages. Please add it to your guild.")
                    .queue();
            return;
        }

        final var start = System.nanoTime();
        var eventName = "messagecontext" + event.getName();
        Commands.messageCommands.stream()
                .filter(s -> eventName.equals(s.getName()))
                .findAny().ifPresent(e -> e.handle(event));

        final var end = System.nanoTime();
        Metrics.latency(start, end, event.getName());
        Metrics.commandCounter();
    }
}
