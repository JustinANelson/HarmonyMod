package com.justinnelson.harmonymod.modules.interactions.commands;

import com.justinnelson.harmonymod.HarmonyMod;
import com.justinnelson.harmonymod.data.entities.ModLogEntity;
import com.justinnelson.harmonymod.data.entities.UserEntity;
import com.justinnelson.harmonymod.data.entities.helpers.MutedMember;
import com.justinnelson.harmonymod.data.entities.helpers.TypeOfModeration;
import com.justinnelson.harmonymod.modules.interactions.commands.commandprocessors.AbstractCommand;
import com.justinnelson.harmonymod.utility.Util;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;

import java.util.ArrayList;
import java.util.List;

public class UserContextCommands extends AbstractCommand {

    public void handle(UserContextInteractionEvent event) {

        String name = event.getName();
        switch (name) {
            case "mute": userContextMute(event); break;
            default:
        }
    }
    public void userContextMute(UserContextInteractionEvent event) {

        Guild guild = event.getGuild();

        boolean keepRolesOnMute = HarmonyMod.db.getGuildBoolValue(guild.getId(), "keepRolesOnMute");
        String guildMutedRole = HarmonyMod.db.getGuildStringValue(guild.getId(), "mutedRole");

        Role muteRole;
        String message = null;

        Member member = event.getTargetMember();

        //Find muted role
        if (guildMutedRole == null) {
            muteRole = guild.getRoles().stream()
                    .filter(r -> r.getName().equalsIgnoreCase("muted")).findFirst()
                    .orElse(null);
        } else {
            muteRole = guild.getRoleById(guildMutedRole);
        }

        //If member is already muted, unmute them.
        if (member.getRoles().contains(muteRole)) {

            guild.removeRoleFromMember(member, muteRole).queue();

            //if keepRolesOnMute is toggled off restore roles from DB.
            if (!keepRolesOnMute) {
                MutedMember mutedMember = HarmonyMod.db.getMutedMember(member);
                for (int x = 0; x < mutedMember.removedRoles.size(); x++) {
                    guild.addRoleToMember(member, guild.getRoleById(mutedMember.removedRoles.get(x))).queue();
                }
            }
            //Remove muted member entry from this user in the DB
            HarmonyMod.db.removeMutedMember(member);

            //Log this interaction
            ModLogEntity modLogEntity = new ModLogEntity(event.getGuild(), member, event.getMember(),
                    TypeOfModeration.UNMUTE, message);
            HarmonyMod.db.addModLogEntry(modLogEntity);
        } else {
            /*
                Mute member with optional remove existing roles
             */
            MutedMember mutedMember = new MutedMember();
            mutedMember.setGuildID(guild.getId());

            //if keep roles is toggled off remove current roles and store in DB.
            if (!keepRolesOnMute) {
                ArrayList<String> removedRoles = new ArrayList<>();
                List<Role> roles = member.getRoles();
                for(Role r: roles){
                    member.getGuild().removeRoleFromMember(member, r).queue();
                    removedRoles.add(r.getId());
                }
                mutedMember.setRemovedRoles(removedRoles);
            }

            //Add mute role to member.
            event.getGuild().addRoleToMember(member, muteRole).queue();

            boolean userExists = HarmonyMod.db.checkUserExists(member);
            if (userExists) {
                //If user already exists in DB just add a mutedMember object.
                HarmonyMod.db.addMutedMember(mutedMember, member.getId());
            } else {
                //Create new userEntity and add mute information including existing roles if any.
                UserEntity userEntity = new UserEntity();
                userEntity.setId(member.getId());
                userEntity.addMutedMember(mutedMember);

                //Store user in DB
                HarmonyMod.db.addUser(userEntity);
            }

            //Create a new mod log entry.
            ModLogEntity modLogEntity = new ModLogEntity(event.getGuild(), member, event.getMember(),
                    TypeOfModeration.MUTE, message);
            HarmonyMod.db.addModLogEntry(modLogEntity);

        }

        Util.standardSuccess(event);

    }
}
