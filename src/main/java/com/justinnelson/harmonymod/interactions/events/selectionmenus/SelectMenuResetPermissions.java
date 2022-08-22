package com.justinnelson.harmonymod.interactions.events.selectionmenus;

import com.justinnelson.harmonymod.core.utility.Util;
import com.justinnelson.harmonymod.interactions.events.eventprocessors.AbstractSelectMenuHandler;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;

import java.util.Objects;

public class SelectMenuResetPermissions extends AbstractSelectMenuHandler {

    @Override
    public void handle(SelectMenuInteractionEvent event) {
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
