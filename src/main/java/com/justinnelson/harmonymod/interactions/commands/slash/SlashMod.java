package com.justinnelson.harmonymod.interactions.commands.slash;

import com.justinnelson.harmonymod.data.entities.helpers.ModInteractionHook;
import com.justinnelson.harmonymod.interactions.commands.commandprocessors.AbstractSlashCommander;
import com.justinnelson.harmonymod.interactions.commands.CommandCategory;
import com.justinnelson.harmonymod.data.HMCollections;
import com.justinnelson.harmonymod.interactions.events.customevents.ModerationEvent;
import com.justinnelson.harmonymod.core.utility.Util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.requests.RestAction;

import java.awt.Color;
import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class SlashMod extends AbstractSlashCommander {

    public SlashMod() {
        super(CommandCategory.MODERATION);
    }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        logExecution();

        if (event.getOption("mentionable") != null) {
            InteractionHook hook = event.getHook();
            String target = event.getOption("mentionable").getAsMember().getId();
            String moderator = event.getMember().getId();

            ModInteractionHook modHook = new ModInteractionHook(hook, target, moderator);
            HMCollections.modInteractionHooks.add(modHook);

            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(Color.CYAN);
            Member member = Objects.requireNonNull(event.getOption("mentionable")).getAsMember();

            Instant created = Objects.requireNonNull(member).getTimeCreated().toInstant();
            Instant join = member.getTimeJoined().toInstant();
            Instant now = Instant.now();
            Duration dCreated = Duration.between(created, now);
            Duration dJoin = Duration.between(join, now);
            String createdAge = Util.formatDuration(dCreated);
            String joinAge = Util.formatDuration(dJoin);

            embed.setAuthor(member.getEffectiveName());

            embed.addField("id", member.getId(), true);
            embed.addField("avatar", "[Link](" + member.getEffectiveAvatarUrl() + ")", true);
            embed.addField("account created", member.getTimeCreated().format(DateTimeFormatter.RFC_1123_DATE_TIME), true);
            embed.addField("account age", createdAge, true);
            embed.addField("joined server at", member.getTimeJoined().format(DateTimeFormatter.RFC_1123_DATE_TIME), true);
            embed.addField("join server age", joinAge, true);
            embed.addField("status", member.getOnlineStatus().toString(), false);
            embed.addField("roles", String.valueOf(member.getRoles()), false);
            embed.setFooter("id: " + event.getId());
            embed.setThumbnail(member.getEffectiveAvatarUrl());
            MessageEmbed msgEmbed = embed.build();
            event.replyEmbeds(msgEmbed)
                    .addActionRow(
                            Button.primary( target+"panelmute", "(un)mute"), // Button with only a label
                            Button.primary(target+"paneltimeout", "timeout"), // Button with only a label
                            Button.primary(target+"panelnickname", "nickname") // Button with only a label
                    ).addActionRow(
                            Button.primary(target+"panellookup", "lookup"), // Button with only a label
                            Button.primary(target+"panelban", "ban")// Button with only a label
                    ).addActionRow(
                            Button.primary(target+"panelkick", "kick"), // Button with only a label
                            Button.primary(target+"panelwarn", "warn"), // Button with only a label
                            Button.primary(target+"panelmoderations", "moderations")// Button with only a label
                    ).queue();
        } else {
            String moderator = event.getMember().getId();

            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(Color.CYAN);

            embed.setAuthor(Objects.requireNonNull(event.getMember()).getEffectiveName());
            embed.addField("roles", String.valueOf(event.getMember().getRoles()), false);
            embed.setThumbnail(event.getMember().getEffectiveAvatarUrl());
            MessageEmbed msgEmbed = embed.build();
            event.replyEmbeds(msgEmbed)
                    .addActionRow(
                            Button.primary(moderator+"togglerole", "togglerole"), // Button with only a label
                            Button.primary(moderator+"fixpermissions", "fixperms"),
                            Button.primary(moderator+"resetpermissions", "resetperms")
                    ).queue();
        }
    }
}
