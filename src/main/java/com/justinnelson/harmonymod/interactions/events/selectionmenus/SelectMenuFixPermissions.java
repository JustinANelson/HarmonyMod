package com.justinnelson.harmonymod.interactions.events.selectionmenus;

import com.justinnelson.harmonymod.core.utility.Util;
import com.justinnelson.harmonymod.interactions.events.eventprocessors.AbstractSelectMenuHandler;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Channel;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.managers.Manager;
import net.dv8tion.jda.api.managers.channel.ChannelManager;

import java.util.Objects;

public class SelectMenuFixPermissions extends AbstractSelectMenuHandler {

    @Override
    public void handle(SelectMenuInteractionEvent event) {
        if(event.getValues().contains("muted")) {
            (event.getGuild()).getChannels(false).forEach(channel -> {
                if(channel.getType() == ChannelType.TEXT) {
                    channel.getPermissionContainer()
                            .upsertPermissionOverride(((event.getGuild().getRoles().stream()
                                    .filter(role -> role.getName().equalsIgnoreCase("muted")).findFirst()
                                    .orElse(null))))
                            .deny(Permission.MESSAGE_SEND)
                            .deny(Permission.MESSAGE_SEND_IN_THREADS)
                            .queue();
                }
                if(channel.getType() == ChannelType.VOICE) {
                    channel.getPermissionContainer()
                            .upsertPermissionOverride(((event.getGuild().getRoles().stream()
                                    .filter(role -> role.getName().equalsIgnoreCase("muted")).findFirst()
                                    .orElse(null))))
                            .deny(Permission.VOICE_SPEAK)
                            .deny(Permission.VOICE_START_ACTIVITIES)
                            .deny(Permission.VOICE_STREAM)
                            .queue();
                }
                if(channel.getType() == ChannelType.VOICE) {
                    channel.getPermissionContainer()
                            .upsertPermissionOverride(((event.getGuild().getRoles().stream()
                                    .filter(role -> role.getName().equalsIgnoreCase("muted")).findFirst()
                                    .orElse(null))))
                            .deny(Permission.VOICE_SPEAK)
                            .deny(Permission.VOICE_START_ACTIVITIES)
                            .deny(Permission.VOICE_STREAM)
                            .queue();
                }
            });
            Util.standardSuccess(event);
        } else if(event.getValues().contains("timeout")) {
            event.getGuild().getChannels(false).forEach(channel -> {
                //TODO add channel exceptions per guild for airlock channel.
                if(channel.getType() == ChannelType.TEXT) {
                    channel.getPermissionContainer()
                            .upsertPermissionOverride(Objects.requireNonNull(event.getGuild().getRoles().stream()
                                    .filter(role -> role.getName().equalsIgnoreCase("timeout")).findFirst()
                                    .orElse(null)))
                            .deny(Permission.VIEW_CHANNEL)
                            .queue();
                }
            });
            Util.standardSuccess(event);
        }
    }
}
