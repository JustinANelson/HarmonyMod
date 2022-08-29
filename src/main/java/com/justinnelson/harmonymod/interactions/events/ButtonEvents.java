package com.justinnelson.harmonymod.interactions.events;

import com.justinnelson.harmonymod.core.HarmonyMod;
import com.justinnelson.harmonymod.data.entities.ModLogEntity;
import com.justinnelson.harmonymod.data.entities.UserEntity;
import com.justinnelson.harmonymod.data.entities.helpers.MutedMember;
import com.justinnelson.harmonymod.data.entities.helpers.TypeOfModeration;
import com.justinnelson.harmonymod.interactions.events.eventprocessors.AbstractEvent;
import com.justinnelson.harmonymod.utility.Util;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;

import java.util.ArrayList;
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
        if (event.getMember().hasPermission(Permission.MANAGE_SERVER)) {
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
        } else {
            //TODO Standard failure event reply.
        }
    }
    public void buttonLookup(ButtonInteractionEvent event, String id) {
        //TODO return moderations for selected member.
    }
    public void buttonMute(ButtonInteractionEvent event, String id) {
        Guild guild = event.getGuild();
        Member member = guild.getMemberById(id);
        boolean removeRolesOnMute = HarmonyMod.db.getBoolValue(guild.getId(), "removeRolesOnMute");
        String guildMutedRole = HarmonyMod.db.getStringValue(guild.getId(), "mutedRole");
        Role muteRole;
        String message = null;

        if (guildMutedRole == null) {
            muteRole = guild.getRoles().stream()
                    .filter(r -> r.getName().equalsIgnoreCase("muted")).findFirst()
                    .orElse(null);
        } else {
            muteRole = guild.getRoleById(guildMutedRole);
        }
        /*
            Unmute already muted member.
         */
        if (member.getRoles().contains(muteRole)) {
            guild.removeRoleFromMember(member, muteRole).queue();

            if (removeRolesOnMute) {
                message = "{Roles restored}";
            }
            //TODO Add removed roles back to member.

            ModLogEntity  modLogEntity = new ModLogEntity(event.getGuild(), member, event.getMember(),
                    TypeOfModeration.UNMUTE, "");
            HarmonyMod.db.addModLogEntry(modLogEntity);

        } else {
            /*
                Mute member with optional remove existing roles
             */
            MutedMember mutedMember = new MutedMember();
            mutedMember.setGuildID(guild.getId());
            mutedMember.setMemberID(member.getId());
            ArrayList<String> removedRoles = new ArrayList<>();
            if (!removeRolesOnMute) {
                List<Role> roles = member.getRoles();
                for(Role r: roles){
                    member.getGuild().removeRoleFromMember(member, r).queue();
                    removedRoles.add(r.getId());
                }
                mutedMember.setRemovedRoles(removedRoles);
                message = "{Roles removed} ";
            }

            //Add mute role to member.
            event.getGuild().addRoleToMember(member, muteRole).queue();

            UserEntity userEntity = new UserEntity();
            userEntity.setId(member.getId());
            userEntity.addMutedMember(mutedMember);

            //Store user in DB
            HarmonyMod.db.addUser(userEntity);

            //Create a new mod log entry.
            ModLogEntity modLogEntity = new ModLogEntity(event.getGuild(), member, event.getMember(),
                    TypeOfModeration.MUTE, message);
            HarmonyMod.db.addModLogEntry(modLogEntity);

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
        if(event.getMember().hasPermission(Permission.MANAGE_SERVER)) {
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
        //standard failure - wrong permissions.

    }
    public void buttonTimeout(ButtonInteractionEvent event, String id) {

    }
}
