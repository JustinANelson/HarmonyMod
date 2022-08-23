package com.justinnelson.harmonymod.interactions.events.buttons;

import com.justinnelson.harmonymod.core.HarmonyMod;
import com.justinnelson.harmonymod.core.utility.Util;
import com.justinnelson.harmonymod.data.entities.ModLogEntity;
import com.justinnelson.harmonymod.data.entities.helpers.ModInteractionHook;
import com.justinnelson.harmonymod.data.entities.helpers.TypeOfModeration;
import com.justinnelson.harmonymod.interactions.events.eventprocessors.AbstractButtonHandler;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public class ButtonPanelMute extends AbstractButtonHandler {

    @Override
    public void handle(ButtonInteractionEvent event, String id) {
        logExecution();

        Member member = event.getGuild().getMemberById(id);
        Role role = event.getGuild().getRoles().stream()
                .filter(r -> r.getName().equalsIgnoreCase("muted")).findFirst()
                .orElse(null);
        if (member.getRoles().contains(role))
        {

            //add all removed roles from member
            event.getGuild().removeRoleFromMember(member, role).queue();
            ModLogEntity  modLogEntity = new ModLogEntity(event.getGuild(), member, event.getMember(),
                    TypeOfModeration.MUTE, "Actin' a fool.");
            HarmonyMod.db.newModLogEntry(modLogEntity);

        } else {

            //remove all existing rules from member
            event.getGuild().addRoleToMember(member, role).queue();

            ModLogEntity  modLogEntity = new ModLogEntity(event.getGuild(), member, event.getMember(),
                    TypeOfModeration.UNMUTE, "Back in compliance.");
            HarmonyMod.db.newModLogEntry(modLogEntity);

        }

        Util.standardSuccess(event);
    }
}
