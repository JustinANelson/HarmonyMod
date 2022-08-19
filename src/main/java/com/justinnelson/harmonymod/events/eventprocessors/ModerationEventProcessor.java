package com.justinnelson.harmonymod.events.eventprocessors;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.Color;

public class ModerationEventProcessor {

    public static void mute(ButtonInteractionEvent event, InteractionHook hook, Member member){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.CYAN);
        embed.setDescription("Mute or Unmute " + member.getEffectiveName());
        MessageEmbed msgEmbed = embed.build();
        event.editMessageEmbeds(msgEmbed).setActionRow(
                Button.primary("modpanelmutemute", "mute"), // Button with only a label
                Button.primary("modpanelmuteunmute", "unmute") // Button with only a label
        ).queue();
    }

}
