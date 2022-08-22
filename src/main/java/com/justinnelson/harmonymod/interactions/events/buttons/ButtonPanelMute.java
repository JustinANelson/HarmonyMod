package com.justinnelson.harmonymod.interactions.events.buttons;

import com.justinnelson.harmonymod.core.utility.Util;
import com.justinnelson.harmonymod.data.entities.helpers.ModInteractionHook;
import com.justinnelson.harmonymod.interactions.events.eventprocessors.AbstractButtonHandler;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public class ButtonPanelMute extends AbstractButtonHandler {

    @Override
    public void handle(ButtonInteractionEvent event, String id) {
        System.out.println(id);
        Member member = event.getGuild().getMemberById(id);
        System.out.println(member.getEffectiveName());
        Role role = event.getGuild().getRoles().stream()
                .filter(r -> r.getName().equalsIgnoreCase("muted")).findFirst()
                .orElse(null);
        event.getGuild().addRoleToMember(member, role).queue();

        Util.standardSuccess(event);
    }
}
