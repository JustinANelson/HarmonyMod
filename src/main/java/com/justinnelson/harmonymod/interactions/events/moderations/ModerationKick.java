package com.justinnelson.harmonymod.interactions.events.moderations;

import com.justinnelson.harmonymod.interactions.commands.CommandCategory;
import com.justinnelson.harmonymod.interactions.events.customevents.ModerationEvent;
import com.justinnelson.harmonymod.interactions.events.eventprocessors.AbstractModerationHandler;

public class ModerationKick extends AbstractModerationHandler {

    public ModerationKick() {
        super(CommandCategory.MODERATION);
    }
    @Override
    public void handle(ModerationEvent event) {

    }
}
