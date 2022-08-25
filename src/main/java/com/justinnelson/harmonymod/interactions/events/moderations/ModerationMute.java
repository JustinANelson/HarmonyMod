package com.justinnelson.harmonymod.interactions.events.moderations;

import com.justinnelson.harmonymod.interactions.commands.commandprocessors.CommandCategory;
import com.justinnelson.harmonymod.interactions.events.customevents.ModerationEvent;
import com.justinnelson.harmonymod.interactions.events.eventprocessors.AbstractModerationHandler;

public class ModerationMute extends AbstractModerationHandler {

    public ModerationMute() {
        super(CommandCategory.MODERATION);
    }

    @Override
    public void handle(ModerationEvent event) {

    }
}
