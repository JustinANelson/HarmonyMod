package com.justinnelson.harmonymod.interactions.events.moderations;

import com.justinnelson.harmonymod.interactions.commands.CommandCategory;
import com.justinnelson.harmonymod.interactions.events.customevents.ModerationEvent;
import com.justinnelson.harmonymod.interactions.events.eventprocessors.AbstractModerationHandler;

public class ModerationBan extends AbstractModerationHandler {

    public ModerationBan() {
        super(CommandCategory.MODERATION);
    }

    @Override
    public void handle(ModerationEvent event) {

    }
}
