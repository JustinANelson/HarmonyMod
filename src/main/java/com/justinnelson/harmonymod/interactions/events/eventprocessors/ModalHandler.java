package com.justinnelson.harmonymod.interactions.events.eventprocessors;

import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;

public interface ModalHandler {
    void handle(ModalInteractionEvent event);
    void read();
    String getName();
}
