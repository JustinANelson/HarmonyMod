package com.justinnelson.harmonymod.interactions.commands.slash;

import com.justinnelson.harmonymod.interactions.commands.commandprocessors.AbstractSlashCommander;
import com.justinnelson.harmonymod.interactions.commands.CommandCategory;
import com.justinnelson.harmonymod.data.HMCollections;
import com.justinnelson.harmonymod.interactions.events.customevents.ModerationEvent;
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

        if (event.getOption("mentionable") != null) {

            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(Color.CYAN);
            Member member = Objects.requireNonNull(event.getOption("mentionable")).getAsMember();

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
            embed.addField("roles", String.valueOf(member.getRoles()), false);
            embed.setFooter("id: " + event.getId());
            embed.setThumbnail(member.getEffectiveAvatarUrl());
            MessageEmbed msgEmbed = embed.build();
            event.replyEmbeds(msgEmbed)
                    .addActionRow(
                            Button.primary("panelmute", "mute"), // Button with only a label
                            Button.primary("paneltimeout", "timeout"), // Button with only a label
                            Button.primary("panelnickname", "nickname") // Button with only a label
                    ).addActionRow(
                            Button.primary("panellookup", "lookup"), // Button with only a label
                            Button.primary("panelban", "ban"),// Button with only a label
                            Button.primary("panelcopyid", "copyid")
                    ).addActionRow(
                            Button.primary("panelkick", "kick"), // Button with only a label
                            Button.primary("panelwarn", "warn"), // Button with only a label
                            Button.primary("panelmoderations", "moderations")// Button with only a label
                    ).queue();
        } else {

        }

    }
}
