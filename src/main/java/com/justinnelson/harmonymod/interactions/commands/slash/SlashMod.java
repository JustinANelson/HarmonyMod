package com.justinnelson.harmonymod.interactions.commands.slash;

import com.justinnelson.harmonymod.interactions.commands.commandprocessors.AbstractSlashCommander;
import com.justinnelson.harmonymod.interactions.commands.CommandCategory;
import com.justinnelson.harmonymod.data.HMCollections;
import com.justinnelson.harmonymod.interactions.commands.events.customevents.ModerationEvent;
import com.justinnelson.harmonymod.core.utility.Util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

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

        ModerationEvent moderationEvent = new ModerationEvent();
        moderationEvent.setName(event.getMember().getEffectiveName());
        moderationEvent.setModPanelMember(Objects.requireNonNull(event.getOption("mentionable")).getAsMember());
        moderationEvent.setHook(event.getHook());
        HMCollections.moderationEvents.add(moderationEvent);

        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.CYAN);
        Member member = moderationEvent.getModPanelMember();

        Instant created = member.getTimeCreated().toInstant();
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
        embed.addField("role", String.valueOf(member.getRoles()), false);
        embed.setFooter("id: " + event.getId());
        embed.setThumbnail(member.getEffectiveAvatarUrl());
        MessageEmbed msgEmbed = embed.build();
        event.replyEmbeds(msgEmbed)
                .addActionRow(
                        Button.primary("mute", "mute"), // Button with only a label
                        Button.primary("timeout", "timeout"), // Button with only a label
                        Button.primary("nickname", "nickname") // Button with only a label
                ).addActionRow(
                        Button.primary("lookup", "lookup"), // Button with only a label
                        Button.primary("ban", "ban")// Button with only a label
                ).addActionRow(
                        Button.primary("kick", "kick"), // Button with only a label
                        Button.primary("warn", "warn"), // Button with only a label
                        Button.primary("moderations", "moderations")// Button with only a label
                ).queue();
    }
}
