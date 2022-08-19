package com.justinnelson.harmonymod.interactions.commands.slash;

import com.justinnelson.harmonymod.interactions.commands.commandprocessors.AbstractSlashCommander;
import com.justinnelson.harmonymod.interactions.commands.CommandCategory;
import com.justinnelson.harmonymod.core.HarmonyMod;
import com.justinnelson.harmonymod.core.utility.Util;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class SlashPrefix extends AbstractSlashCommander {

    public SlashPrefix(){
        super(CommandCategory.MODERATION);
    }

    @Override
    public void handle(SlashCommandInteractionEvent event){
        trace(this.getClass().getSimpleName() + " executed");
        event.replyEmbeds(Util.basicMessageEmbed(HarmonyMod.botConfig.getCustomPrefix())).queue();
    }
}
