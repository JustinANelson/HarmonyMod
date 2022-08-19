package com.justinnelson.harmonymod.interactions.events.buttons;

import com.justinnelson.harmonymod.interactions.commands.CommandCategory;
import com.justinnelson.harmonymod.interactions.events.eventprocessors.AbstractButtonHandler;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.Color;

public class ButtonMute extends AbstractButtonHandler {

    public ButtonMute() {
        super(CommandCategory.MODERATION);
    }

    @Override
    public void handle(ButtonInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.CYAN);
        //embed.setDescription("Mute or Unmute " + member.getEffectiveName());
        MessageEmbed msgEmbed = embed.build();
        event.editMessageEmbeds(msgEmbed).setActionRow(
                Button.primary("modpanelmutemute", "mute"), // Button with only a label
                Button.primary("modpanelmuteunmute", "unmute") // Button with only a label
        ).queue();
    }
}
