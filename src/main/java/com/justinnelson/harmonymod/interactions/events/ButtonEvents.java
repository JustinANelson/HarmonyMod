package com.justinnelson.harmonymod.interactions.events;

import com.justinnelson.harmonymod.core.HarmonyMod;
import com.justinnelson.harmonymod.utility.Util;
import com.justinnelson.harmonymod.data.entities.ModLogEntity;
import com.justinnelson.harmonymod.data.entities.helpers.TypeOfModeration;
import com.justinnelson.harmonymod.interactions.events.eventprocessors.AbstractEvent;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;

import java.util.List;

public class ButtonEvents extends AbstractEvent {

    public void handle(ButtonInteractionEvent event) {

        var id = event.getButton().getId().substring(0, 18);
        var name = event.getButton().getId().substring(18);

        switch (name) {
            case "autoresponses": buttonAutoresponses(event, id); break;
            case "delmessages": buttonDeleteUserMessage(event, id); break;
            case "fixpermissions": buttonFixPermissions(event, id); break;
            case "lookup": buttonLookup(event, id); break;
            case "mute": buttonMute(event, id); break;
            case "nickname": buttonNickname(event, id); break;
            case "resetpermissions": buttonResetPermissions(event, id); break;
            case "rolebutton": buttonRoleButton(event, id); break;
            case "timeout": buttonTimeout(event, id); break;
        }

    }
    public void buttonAutoresponses(ButtonInteractionEvent event, String id) {

    }
    public void buttonDeleteUserMessage(ButtonInteractionEvent event, String id) {

    }
    public void buttonFixPermissions(ButtonInteractionEvent event, String id) {

        event.getMessage().editMessageComponents()
                .setActionRow(
                        SelectMenu.create("fixpermissions")
                                .setPlaceholder("Choose role to fix.")
                                .setRequiredRange(1, 1)
                                .addOption("Muted", "muted")
                                .addOption("Timeout", "timeout")
                                .build()
                ).queue();
        Util.standardSuccess(event);

    }
    public void buttonLookup(ButtonInteractionEvent event, String id) {

    }
    public void buttonMute(ButtonInteractionEvent event, String id) {


        Member member = event.getGuild().getMemberById(id);

        Role role = event.getGuild().getRoles().stream()
                .filter(r -> r.getName().equalsIgnoreCase("muted")).findFirst()
                .orElse(null);
        if (member.getRoles().contains(role))
        {

            //TODO - add all removed roles from member
            event.getGuild().removeRoleFromMember(member, role).queue();

            ModLogEntity modLogEntity = new ModLogEntity(event.getGuild(), member, event.getMember(),
                    TypeOfModeration.MUTE, "Actin' a fool.");
            HarmonyMod.db.newModLogEntry(modLogEntity);

        } else {

            //TODO - remove all existing rules from member
            event.getGuild().addRoleToMember(member, role).queue();

            ModLogEntity  modLogEntity = new ModLogEntity(event.getGuild(), member, event.getMember(),
                    TypeOfModeration.UNMUTE, "Back in compliance.");
            HarmonyMod.db.newModLogEntry(modLogEntity);

        }

        Util.standardSuccess(event);

    }
    public void buttonNickname(ButtonInteractionEvent event, String id) {

    }
    public void buttonResetPermissions(ButtonInteractionEvent event, String id) {

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
    public void buttonRoleButton(ButtonInteractionEvent event, String id) {
        //TODO make manager only
        String roleName = event.getButton().getLabel();
        Member member = event.getMember();
        List<Role> roles = event.getGuild().getRolesByName(roleName, true);

        for(Role r: roles){
            //Checks if role is toggleable
            if(event.getButton().getId().startsWith("1")){
                //If button role is toggleable remove role if they already have it
                if(member.getRoles().contains(r)){
                    member.getGuild().removeRoleFromMember(member, r).queue();
                }else{
                    //If button role is toggleable add role if they don't have it
                    member.getGuild().addRoleToMember(member, r).queue();
                }
            }else{
                //If role is not toggleable add it to member.
                member.getGuild().addRoleToMember(member, r).queue();
            }
        }
        Util.standardSuccess(event);
    }
    public void buttonTimeout(ButtonInteractionEvent event, String id) {

    }
}
