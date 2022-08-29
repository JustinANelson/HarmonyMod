package com.justinnelson.harmonymod.interactions.commands;

import com.justinnelson.harmonymod.core.HarmonyMod;
import com.justinnelson.harmonymod.data.entities.ModLogEntity;
import com.justinnelson.harmonymod.data.entities.helpers.TypeOfModeration;
import com.justinnelson.harmonymod.interactions.commands.commandprocessors.AbstractCommand;
import com.justinnelson.harmonymod.utility.Util;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;

public class UserContextCommands extends AbstractCommand {

    public void handle(UserContextInteractionEvent event) {

        String name = event.getName();
        switch (name) {
            case "mute": userContextMute(event); break;
            default:
        }
    }
    public void userContextMute(UserContextInteractionEvent event) {

        Member member = event.getTargetMember();

        Role role = event.getGuild().getRoles().stream()
                .filter(r -> r.getName().equalsIgnoreCase("muted")).findFirst()
                .orElse(null);
        if (member.getRoles().contains(role))
        {

            event.getGuild().removeRoleFromMember(member, role).queue();

            ModLogEntity modLogEntity = new ModLogEntity(event.getGuild(), member, event.getMember(),
                    TypeOfModeration.MUTE, "Actin' a fool.");
            HarmonyMod.db.addModLogEntry(modLogEntity);

        } else {

            //TODO - remove all existing rules from member - copy from button
            event.getGuild().addRoleToMember(member, role).queue();

            ModLogEntity  modLogEntity = new ModLogEntity(event.getGuild(), member, event.getMember(),
                    TypeOfModeration.UNMUTE, "Back in compliance.");
            HarmonyMod.db.addModLogEntry(modLogEntity);
        }

        Util.standardSuccess(event);

    }
}
