package com.justinnelson.harmonymod.interactions.events.eventprocessors;

import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;

public interface SelectMenuHandler {

    void handle(SelectMenuInteractionEvent event);
    void read();
    String getName();

}
