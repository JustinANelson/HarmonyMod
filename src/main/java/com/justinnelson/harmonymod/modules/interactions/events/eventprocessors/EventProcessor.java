package com.justinnelson.harmonymod.modules.interactions.events.eventprocessors;

import com.justinnelson.harmonymod.data.Metrics;
import com.justinnelson.harmonymod.modules.interactions.events.AutoResponderEvents;
import com.justinnelson.harmonymod.modules.interactions.events.ButtonEvents;
import com.justinnelson.harmonymod.modules.interactions.events.ModalEvents;
import com.justinnelson.harmonymod.modules.interactions.events.ModerationEvents;
import com.justinnelson.harmonymod.modules.interactions.events.SelectMenuEvents;

import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventProcessor {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    protected void info(String message){ log.info(message); }
    protected void error(String message){ log.error(message); }
    protected void debug(String message){ log.debug(message); }
    protected void trace(String message){ log.trace(message); }
    protected void warn(String message){ log.warn(message); }

    AutoResponderEvents autoResponderEvents;
    ButtonEvents buttonEvents;
    ModalEvents modalEvents;
    ModerationEvents moderationEvents;
    SelectMenuEvents selectMenuEvents;

    public EventProcessor() {
        autoResponderEvents = new AutoResponderEvents();
        buttonEvents = new ButtonEvents();
        modalEvents = new ModalEvents();
        moderationEvents = new ModerationEvents();
        selectMenuEvents = new SelectMenuEvents();
    }
    public void process(ButtonInteractionEvent event) {
        final var start = System.nanoTime();
        var id = event.getButton().getId().substring(0, 18);
        var eventName = event.getButton().getId().substring(18);

        buttonEvents.handle(event);

        final var end = System.nanoTime();
        Metrics.latency(start, end, event.getButton().getId());
        Metrics.commandCounter();

        logExecution(eventName);
    }
    public void process(SelectMenuInteractionEvent event) {
        final var start = System.nanoTime();
        var eventName = event.getComponent().getId();

        selectMenuEvents.handle(event);

        final var end = System.nanoTime();
        Metrics.latency(start, end, event.getComponent().getId());
        Metrics.commandCounter();

        logExecution(eventName);
    }
    public void process(ModalInteractionEvent event) {
        final var start = System.nanoTime();
        var eventName = event.getModalId();

        modalEvents.handle(event);

        final var end = System.nanoTime();
        Metrics.latency(start, end, event.getModalId());
        Metrics.commandCounter();

        logExecution(eventName);
    }
    public void logExecution(String name) {
        trace(name + " executed.");
    }
}
