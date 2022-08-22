package com.justinnelson.harmonymod.interactions.events.buttons;

import com.justinnelson.harmonymod.interactions.commands.CommandCategory;
import com.justinnelson.harmonymod.core.utility.Util;
import com.justinnelson.harmonymod.interactions.events.eventprocessors.AbstractButtonHandler;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

import java.util.List;

public class ButtonToggleRole extends AbstractButtonHandler {

    @Override
    public void handle(ButtonInteractionEvent event, String id) {
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
}
