package com.justinnelson.harmonymod.interactions.events.modals;

import com.justinnelson.harmonymod.core.HarmonyMod;
import com.justinnelson.harmonymod.core.utility.Util;
import com.justinnelson.harmonymod.interactions.commands.commandprocessors.CommandCategory;
import com.justinnelson.harmonymod.interactions.events.eventprocessors.AbstractModalHandler;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;

public class ModalFeedback extends AbstractModalHandler {

    public ModalFeedback() {
        super(CommandCategory.UTILS);
    }

    @Override
    public void handle(ModalInteractionEvent event){
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
