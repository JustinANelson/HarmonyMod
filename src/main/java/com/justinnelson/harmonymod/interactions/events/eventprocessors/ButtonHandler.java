package com.justinnelson.harmonymod.interactions.events.eventprocessors;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public interface ButtonHandler {

    void handle(ButtonInteractionEvent event, String id);
    void read();
    String getName();

}
