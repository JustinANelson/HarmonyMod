package com.justinnelson.harmonymod.commands.context;

import com.justinnelson.harmonymod.commands.CommandCategory;
import com.justinnelson.harmonymod.commands.commandprocessors.AbstractUserContextCommander;
import com.justinnelson.harmonymod.utility.Util;

import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;

/*
    Currently not operational.
    Need to send the success message to a specified channel.
 */
public class UserContextMute extends AbstractUserContextCommander {

    public UserContextMute() {
        super(CommandCategory.MODERATION);
    }

    @Override
    public void handle(UserContextInteractionEvent event) {
        Util.standardSuccess(event);
    }

    @Override
    public String getName() {
        return null;
    }
}
