package com.justinnelson.harmonymod.interactions.events.eventprocessors;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;

public interface ButtonPanelHandler {

    void handle(ButtonInteractionEvent event);
    void read();
    String getName();
    InteractionHook getHook();
    void setHook(InteractionHook hook);

}
