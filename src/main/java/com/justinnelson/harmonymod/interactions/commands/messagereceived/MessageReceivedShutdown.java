package com.justinnelson.harmonymod.interactions.commands.messagereceived;

import com.justinnelson.harmonymod.interactions.commands.CommandCategory;
import com.justinnelson.harmonymod.interactions.commands.commandprocessors.AbstractMessageReceivedCommander;
import com.justinnelson.harmonymod.core.HarmonyMod;
import com.justinnelson.harmonymod.data.AppConfig;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MessageReceivedShutdown extends AbstractMessageReceivedCommander {

    public MessageReceivedShutdown() {
        super(CommandCategory.OWNER);
    }

    @Override
    public void handle(MessageReceivedEvent event) {
        if (event.getAuthor().getId().equals(AppConfig.OWNER)) {
            HarmonyMod.jda.shutdown();
        }
        else {
            warn("{ID:" + event.getAuthor().getId() + "}{Name:" + event.getAuthor().getName() + "}{Guild:" + event.getGuild().getName() + "} tried to shutdown the bot.");
        }
    }
}
