package com.justinnelson.harmonymod.modules.interactions.events;

import com.justinnelson.harmonymod.modules.interactions.events.customevents.AutoResponderInteractionEvent;
import com.justinnelson.harmonymod.modules.interactions.events.eventprocessors.AbstractEvent;

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
