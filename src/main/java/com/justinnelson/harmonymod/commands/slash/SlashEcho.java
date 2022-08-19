package com.justinnelson.harmonymod.commands.slash;

import com.justinnelson.harmonymod.commands.commandprocessors.AbstractSlashCommander;
import com.justinnelson.harmonymod.commands.CommandCategory;
import com.justinnelson.harmonymod.utility.Util;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class SlashEcho extends AbstractSlashCommander {

    public SlashEcho(){
        super(CommandCategory.UTILS);
    }

    @Override
    public void handle(SlashCommandInteractionEvent event){
        logExecution();
        if (event.getOption("msg") != null) {
            if (event.getOption("channelid") != null) {
                TextChannel channel = (TextChannel) event.getGuild().getGuildChannelById(event.getOption("channelid").getAsString());
                channel.sendMessageEmbeds(Util.basicMessageEmbed(event.getOption("msg").getAsString())).queue();
            }
            event.replyEmbeds(Util.basicMessageEmbed(event.getOption("msg").getAsString())).queue();
        }
    }
}
