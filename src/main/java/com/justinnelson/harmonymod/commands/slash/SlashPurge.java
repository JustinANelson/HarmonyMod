package com.justinnelson.harmonymod.commands.slash;

import com.justinnelson.harmonymod.commands.commandprocessors.AbstractSlashCommander;
import com.justinnelson.harmonymod.commands.CommandCategory;
import com.justinnelson.harmonymod.utility.Util;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SlashPurge extends AbstractSlashCommander {
    public SlashPurge() {
        super(CommandCategory.MODERATION);
    }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        trace(this.getClass().getSimpleName() + " executed");
        if(event.getMember().hasPermission(Permission.MANAGE_CHANNEL)){

            /*
                Add parameters to account for messages being older than 2 weeks.
             */
            List<Message> messages = event.getChannel().getHistory().retrievePast(event.getOption("amount").getAsInt()).complete();
            event.getChannel().asTextChannel().purgeMessages(messages);

        }
        Util.standardSuccess(event);
    }
}
