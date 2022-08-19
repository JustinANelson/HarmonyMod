package com.justinnelson.harmonymod.interactions.events.moderations;

import com.justinnelson.harmonymod.interactions.commands.CommandCategory;
import com.justinnelson.harmonymod.interactions.events.customevents.ModerationEvent;
import com.justinnelson.harmonymod.interactions.events.eventprocessors.AbstractModerationHandler;

public class ModerationLookup extends AbstractModerationHandler {

    public ModerationLookup() {
        super(CommandCategory.MODERATION);
    }

    @Override
    public void handle(ModerationEvent event) {

    }
}
