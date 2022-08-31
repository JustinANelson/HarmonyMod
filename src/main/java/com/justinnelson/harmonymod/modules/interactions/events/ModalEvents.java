package com.justinnelson.harmonymod.modules.interactions.events;

import com.justinnelson.harmonymod.HarmonyMod;
import com.justinnelson.harmonymod.modules.interactions.events.eventprocessors.AbstractEvent;
import com.justinnelson.harmonymod.utility.Util;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;

public class ModalEvents extends AbstractEvent {

    public void handle(ModalInteractionEvent event) {

        String name = event.getModalId();

        switch (name) {
            case "placeholder": placeholder(event); break;
            case "feedback": feedback(event); break;

        }

    }
    public void placeholder(ModalInteractionEvent event) {

    }
    public void feedback(ModalInteractionEvent event) {
        TextChannel textChannel = HarmonyMod.jda.getTextChannelsByName("feedback", true).get(0);
        if (textChannel != null) {
            textChannel.sendMessageEmbeds(
                            Util.titledMessageEmbed(
                                    event.getMember().getEffectiveName(),
                                    event.getValue("subject").getAsString(),
                                    event.getValue("body").getAsString()))
                    .queue();
        }
        Util.standardSuccess(event);
    }
}
