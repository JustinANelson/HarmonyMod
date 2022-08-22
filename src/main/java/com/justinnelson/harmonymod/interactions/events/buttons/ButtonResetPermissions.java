package com.justinnelson.harmonymod.interactions.events.buttons;

import com.justinnelson.harmonymod.core.utility.Util;
import com.justinnelson.harmonymod.interactions.events.eventprocessors.AbstractButtonHandler;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;

public class ButtonResetPermissions extends AbstractButtonHandler {

    @Override
    public void handle(ButtonInteractionEvent event, String id) {
        event.getMessage().editMessageComponents()
                .setActionRow(
                        SelectMenu.create("resetpermissions")
                                .setPlaceholder("Choose role to fix.")
                                .setRequiredRange(1, 1)
                                .addOption("Muted", "muted")
                                .addOption("Timeout", "timeout")
                                .build()
                ).queue();
        Util.standardSuccess(event);
    }
}
