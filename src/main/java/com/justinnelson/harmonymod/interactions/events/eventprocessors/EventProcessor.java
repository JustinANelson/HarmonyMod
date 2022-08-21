package com.justinnelson.harmonymod.interactions.events.eventprocessors;

import com.justinnelson.harmonymod.data.Metrics;
import com.justinnelson.harmonymod.interactions.commands.Commands;
import com.justinnelson.harmonymod.interactions.events.Events;
import com.justinnelson.harmonymod.interactions.events.customevents.ModerationEvent;

import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public class EventProcessor {

    public void process(ButtonInteractionEvent event) {

        Events.buttonPanelEvents.forEach(ButtonPanelHandler::read);

        final var start = System.nanoTime();
        var eventName = "button" + event.getButton().getId();
        Events.buttonPanelEvents.stream()
                .filter(s -> eventName.equals(s.getName()))
                .findAny().ifPresent(e -> e.handle(event));

        final var end = System.nanoTime();
        Metrics.latency(start, end, event.getButton().getId());
        Metrics.commandCounter();
    }
    public void process(ModalInteractionEvent event) {

    }
    public void process(ModerationEvent event) {

    }
}
