package com.justinnelson.harmonymod.interactions.events.eventprocessors;

import com.justinnelson.harmonymod.data.Metrics;
import com.justinnelson.harmonymod.interactions.commands.Commands;
import com.justinnelson.harmonymod.interactions.events.Events;
import com.justinnelson.harmonymod.interactions.events.customevents.ModerationEvent;

import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;

public class EventProcessor {

    public void process(ButtonInteractionEvent event) {

        System.out.println(event.getButton().getId());
        final var start = System.nanoTime();
        var id = event.getButton().getId().substring(0, 18);
        var eventName = "button" + event.getButton().getId().substring(18);
        System.out.println(eventName);
        Events.buttonEvents.stream()
                .filter(s -> eventName.equals(s.getName()))
                .findAny().ifPresent(e -> e.handle(event, id));

        final var end = System.nanoTime();
        Metrics.latency(start, end, event.getButton().getId());
        Metrics.commandCounter();

    }
    public void process(ModalInteractionEvent event) {

    }
    public void process(ModerationEvent event) {

    }
    public void process(SelectMenuInteractionEvent event) {

        System.out.println(event.getComponent().getId());
        final var start = System.nanoTime();
        var eventName = "selectmenu" + event.getComponent().getId();
        Events.selectMenuEvents.stream()
                .filter(s -> eventName.equals(s.getName()))
                .findAny().ifPresent(e -> e.handle(event));

        final var end = System.nanoTime();
        Metrics.latency(start, end, event.getComponent().getId());
        Metrics.commandCounter();

    }
}
