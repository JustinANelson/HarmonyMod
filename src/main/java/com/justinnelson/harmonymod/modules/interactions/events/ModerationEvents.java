package com.justinnelson.harmonymod.modules.interactions.events;

import com.justinnelson.harmonymod.modules.interactions.events.customevents.ModerationInteractionEvent;
import com.justinnelson.harmonymod.modules.interactions.events.eventprocessors.AbstractEvent;

public class ModerationEvents extends AbstractEvent {

    public void handle(ModerationInteractionEvent event) {
        String name = event.getName();

        switch (name) {
            case "placeholder": placeholder(event); break;
            case "ban": ban(event); break;
            case "kick": kick(event); break;
            case "lookup": lookup(event); break;
            case "mute": mute(event); break;
            case "timeout": timeout(event); break;
        }
    }
    public void placeholder(ModerationInteractionEvent event) {

    }
    public void ban(ModerationInteractionEvent event) {

    }
    public void kick(ModerationInteractionEvent event) {

    }
    public void lookup(ModerationInteractionEvent event) {

    }
    public void mute(ModerationInteractionEvent event) {

    }
    public void timeout(ModerationInteractionEvent event) {

    }

}
