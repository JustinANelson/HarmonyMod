package com.justinnelson.harmonymod.interactions.commands;

import com.justinnelson.harmonymod.core.HarmonyMod;
import com.justinnelson.harmonymod.interactions.commands.commandprocessors.AbstractCommand;
import com.justinnelson.harmonymod.utility.Util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Modal;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.TimeZone;

public class SlashCommands extends AbstractCommand {

    public void handle(SlashCommandInteractionEvent event) {

        String name = event.getName();
        switch (name) {
            case "echo": slashEcho(event); break;
            case "feedback": slashFeedback(event); break;
            case "mod": slashMod(event); break;
            case "manager": slashManager(event); break;
            case "ping": slashPing(event); break;
            case "prefix": slashPrefix(event); break;
            case "purge": slashPurge(event); break;
            case "rolebutton": slashRoleButton(event); break;
            case "time": slashTime(event); break;
            default:
        }
    }
    public void slashEcho(SlashCommandInteractionEvent event) {

        if (event.getOption("msg") != null) {
            if (event.getOption("channelid") != null) {
                TextChannel channel = (TextChannel) event.getGuild().getGuildChannelById(event.getOption("channelid").getAsString());
                channel.sendMessageEmbeds(Util.basicMessageEmbed(event.getOption("msg").getAsString())).queue();
            }
            event.replyEmbeds(Util.basicMessageEmbed(event.getOption("msg").getAsString())).queue();
        }
    }
    public void slashFeedback(SlashCommandInteractionEvent event) {

        TextInput subject = TextInput.create("subject", "Subject", TextInputStyle.PARAGRAPH)
                .setPlaceholder("subject line")
                .setMinLength(4)
                .setMaxLength(30)
                .build();

        TextInput body = TextInput.create("body", "Body", TextInputStyle.PARAGRAPH)
                .setPlaceholder("Feedback")
                .setMinLength(10)
                .setMaxLength(300)
                .build();

        Modal modal = Modal.create("feedback", "Feedback")
                .addActionRows(ActionRow.of(subject), ActionRow.of(body))
                .build();
        event.replyModal(modal).queue();

    }
    public void slashMod(SlashCommandInteractionEvent event) {

        if (event.getOption("mentionable") != null) {
            InteractionHook hook = event.getHook();
            String target = event.getOption("mentionable").getAsMember().getId();
            String moderator = event.getMember().getId();

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
                            Button.primary( target+"mute", "(un)mute"), // Button with only a label
                            Button.primary(target+"timeout", "timeout"), // Button with only a label
                            Button.primary(target+"nickname", "nickname") // Button with only a label
                    ).addActionRow(
                            Button.primary(target+"lookup", "lookup"), // Button with only a label
                            Button.primary(target+"ban", "ban")// Button with only a label
                    ).addActionRow(
                            Button.primary(target+"kick", "kick"), // Button with only a label
                            Button.primary(target+"warn", "warn"), // Button with only a label
                            Button.primary(target+"moderations", "moderations")// Button with only a label
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
                            Button.primary(moderator+"autoresponses", "autoresponses"),
                            Button.primary(moderator+"fixpermissions", "fixperms"),
                            Button.primary(moderator+"resetpermissions", "resetperms")
                    ).addActionRow(
                            Button.primary(moderator+"deletemessages", "delmessages")
                    ).queue();
        }
    }
    public void slashManager(SlashCommandInteractionEvent event) {

        String manager = event.getMember().getId();

        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.CYAN);

        embed.setAuthor(Objects.requireNonNull(event.getMember()).getEffectiveName());
        embed.addField("roles", String.valueOf(event.getMember().getRoles()), false);
        embed.setThumbnail(event.getMember().getEffectiveAvatarUrl());
        MessageEmbed msgEmbed = embed.build();
        event.replyEmbeds(msgEmbed)
                .addActionRow(
                        Button.primary(manager+"autoresponses", "autoresponses"),
                        Button.primary(manager+"fixpermissions", "fixperms"),
                        Button.primary(manager+"resetpermissions", "resetperms")
                ).addActionRow(
                        Button.primary(manager+"deletemessages", "delmessages")
                ).queue();
        
    }
    public void slashPing(SlashCommandInteractionEvent event) {

        long time = System.currentTimeMillis();
        event.reply("Pong!").setEphemeral(true) // reply or acknowledge
                .flatMap(v ->
                        event.getHook().editOriginalFormat("Pong: %d ms", System.currentTimeMillis() - time) // then edit original
                ).queue(); // Queue both reply and edit

    }
    public void slashPrefix(SlashCommandInteractionEvent event) {

        trace(this.getClass().getSimpleName() + " executed");
        event.replyEmbeds(Util.basicMessageEmbed(HarmonyMod.botConfig.getCustomPrefix())).queue();

    }
    public void slashPurge(SlashCommandInteractionEvent event) {

        if(event.getMember().hasPermission(Permission.MANAGE_CHANNEL)){

            /*
                Add parameters to account for messages being older than 2 weeks.
             */
            List<Message> messages = event.getChannel().getHistory().retrievePast(event.getOption("amount").getAsInt()).complete();
            event.getChannel().asTextChannel().purgeMessages(messages);

        }
        Util.standardSuccess(event);

    }
    public void slashRoleButton(SlashCommandInteractionEvent event) {

        long eventID = new Random().nextLong();

        //Here we are generating a new long to use as the eventID
        //If the button is toggleable we are going to preface the ID with a 1
        //If the button is not toggleable we will preface with a 0.
        String eventIDString = null;

        OptionMapping option = event.getOptions().stream().filter(e -> e.getName().equals("rolebuttontoggle")).findAny().orElse(null);
        if (option != null) {
            if (option.getAsBoolean()) {
                eventIDString = "1" + eventID;
            } else {
                eventIDString = "0" + eventID;
            }
        }

        MessageEmbed msgEmbed;
        OptionMapping option2 = event.getOptions().stream().filter(e -> e.getName().equals("rolebuttondescription")).findAny().orElse(null);
        if (option2 != null){
            msgEmbed = Util.basicMessageEmbed(event.getOption("rolebuttondescription").getAsString());
        } else {
            msgEmbed = Util.basicMessageEmbed("Click button for role.");
        }

        Role role = event.getOption("rolebuttonrole").getAsRole();
        eventIDString = eventIDString + role.getId();
        String label = role.getName();
        event.replyEmbeds(msgEmbed)
                .addActionRow(
                        Button.primary(eventIDString, label) // Button with only a label
                ).queue();

        //Creates new roleButton which adds itself to the roleButtons Collection
        //new ButtonToggleRole(eventIDString, label);

    }
    public void slashTime(SlashCommandInteractionEvent event) {

        boolean ephemeral = true;
        OptionMapping option = event.getOptions().stream().filter(e -> e.getName().equals("ephemeral")).findAny().orElse(null);
        if (option != null) {
            ephemeral = option.getAsBoolean();
        }

        EmbedBuilder time = new EmbedBuilder();
        time.setColor(0x66d8ff);
        time.setTitle("Times across the world");
        time.setImage("https://image.freepik.com/free-vector/polygonal-map-digital-globe-map-blue-polygons-earth-maps-world-internet-connection-3d-grid-illustration_102902-902.jpg");
        time.addField("New York:", tzf("America/New_York"), true);
        time.addField("Chicago:", tzf("America/Chicago"), true);
        time.addField("Denver:", tzf("America/Denver"), true);
        time.addField("Phoenix:", tzf("America/Phoenix"), true);
        time.addField("Los Angeles:", tzf("America/Los_Angeles"), true);
        time.addField("Anchorage:", tzf("America/Anchorage"), true);
        time.addField("Honolulu:", tzf("Pacific/Honolulu"), true);
        time.addField("London:", tzf("Europe/London"), true);
        time.addField("Stockholm:", tzf("Europe/Stockholm"), true);
        time.addField("Perth:", tzf("Australia/Perth"), true);
        time.addField("Adelaide:", tzf("Australia/Adelaide"), true);
        time.addField("Sydney:", tzf("Australia/Sydney"), true);
        time.addField("South Korea:", tzf("Asia/Seoul"), true);
        time.addField("Berlin:", tzf("Europe/Berlin"), true);
        time.addField("Hong Kong:", tzf("Asia/Hong_Kong"), true);

        event.replyEmbeds(time.build()).setEphemeral(ephemeral).queue();

    }
    public String tzf(String timeZone) {
        Date today = new Date();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy - HH:mm:ss z");
        df.setTimeZone(TimeZone.getTimeZone(timeZone));

        return df.format(today);
    };

}
