package com.justinnelson.harmonymod.modules.interactions.events;

import com.justinnelson.harmonymod.utility.Util;
import com.justinnelson.harmonymod.modules.interactions.events.eventprocessors.AbstractEvent;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;

import java.util.Objects;

public class SelectMenuEvents extends AbstractEvent {

    public void handle(SelectMenuInteractionEvent event) {
        String name = event.getComponent().toString();

        switch (name) {
            case "placeholder": placeholder(event); break;
            case "fixpermissions": fixPermissions(event); break;
            case "resetpermissions": resetPermissions(event); break;

        }
    }
    public void placeholder(SelectMenuInteractionEvent event) {

    }
    public void fixPermissions(SelectMenuInteractionEvent event) {
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
    public void resetPermissions(SelectMenuInteractionEvent event) {
        if(event.getValues().contains("muted")) {
            (event.getGuild()).getChannels(false).forEach(channel -> {
                if(channel.getType() == ChannelType.TEXT) {
                    channel.getPermissionContainer()
                            .upsertPermissionOverride(((event.getGuild().getRoles().stream()
                                    .filter(role -> role.getName().equalsIgnoreCase("muted")).findFirst()
                                    .orElse(null))))
                            .clear(Permission.MESSAGE_SEND)
                            .clear(Permission.MESSAGE_SEND_IN_THREADS)
                            .queue();
                }
            });
            Util.standardSuccess(event);
        } else if(event.getValues().contains("timeout")) {
            event.getGuild().getChannels(false).forEach(channel -> {
                if(channel.getType() == ChannelType.TEXT) {
                    channel.getPermissionContainer()
                            .upsertPermissionOverride(Objects.requireNonNull(event.getGuild().getRoles().stream()
                                    .filter(role -> role.getName().equalsIgnoreCase("timeout")).findFirst()
                                    .orElse(null)))
                            .clear(Permission.VIEW_CHANNEL)
                            .queue();
                }
            });
            Util.standardSuccess(event);
        }
    }
}
