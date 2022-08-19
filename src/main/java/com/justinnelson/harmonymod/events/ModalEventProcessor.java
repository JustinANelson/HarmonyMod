package com.justinnelson.harmonymod.events;

import com.justinnelson.harmonymod.core.HarmonyMod;
import com.justinnelson.harmonymod.utility.Util;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;

public class ModalEventProcessor {
    public void process(ModalInteractionEvent event){
        if (event.getModalId().equalsIgnoreCase("feedback")){
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
}