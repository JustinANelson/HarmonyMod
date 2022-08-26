package com.justinnelson.harmonymod.interactions.events;

import com.justinnelson.harmonymod.interactions.events.customevents.AutoResponderInteractionEvent;
import com.justinnelson.harmonymod.interactions.events.eventprocessors.AbstractEvent;

public class AutoResponderEvents extends AbstractEvent {

    public void handle(AutoResponderInteractionEvent event) {

        String name = event.getName();

        switch (name) {
            case "autoresponses": placeholder(event); break;

        }

    }
    public void placeholder(AutoResponderInteractionEvent event) {

    }
}
