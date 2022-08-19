package com.justinnelson.harmonymod.interactions.events.eventprocessors;

import com.justinnelson.harmonymod.interactions.events.customevents.ModerationEvent;

public interface ModerationHandler {
    void handle(ModerationEvent event);
    void read();
    String getName();
}
