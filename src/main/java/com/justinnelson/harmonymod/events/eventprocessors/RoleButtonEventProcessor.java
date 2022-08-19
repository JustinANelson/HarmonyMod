package com.justinnelson.harmonymod.events.eventprocessors;

import static com.justinnelson.harmonymod.utility.Util.basicMessageEmbed;

import com.justinnelson.harmonymod.utility.Util;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RoleButtonEventProcessor {
    private static final Logger log = LoggerFactory.getLogger(RoleButtonEventProcessor.class);

    public static void event(ButtonInteractionEvent event){
        /*for(int x = 0; x < Variables.genericEventData.size(); x++){
            out.debug("genericData eventID: " + Variables.genericEventData.get(x).getEventID());
        }
        for (GenericEventData g: Variables.genericEventData) {
            out.debug(g.getEventID());
            out.debug(event.getComponentId());
            if (g.getEventID().equals(event.getComponentId())) {
                out.debug("Found event.");
                buttonRole(g, event);
            }
        }*/
        buttonToggleRole(event);
    }
    public static void buttonToggleRole(ButtonInteractionEvent event){
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
