package com.justinnelson.harmonymod.commands;

import com.justinnelson.harmonymod.commands.commandprocessors.MessageContextCommander;
import com.justinnelson.harmonymod.commands.commandprocessors.UserContextCommander;
import com.justinnelson.harmonymod.commands.commandprocessors.SlashCommander;
import com.justinnelson.harmonymod.commands.context.UserContextMute;
import com.justinnelson.harmonymod.commands.message.MessageContextReportMessage;
import com.justinnelson.harmonymod.commands.slash.SlashEcho;
import com.justinnelson.harmonymod.commands.slash.SlashFeedback;
import com.justinnelson.harmonymod.commands.slash.SlashMenu;
import com.justinnelson.harmonymod.commands.slash.SlashMod;
import com.justinnelson.harmonymod.commands.slash.SlashPing;
import com.justinnelson.harmonymod.commands.slash.SlashPrefix;
import com.justinnelson.harmonymod.commands.slash.SlashPurge;
import com.justinnelson.harmonymod.commands.slash.SlashRoleButton;
import com.justinnelson.harmonymod.commands.slash.SlashTime;
import com.justinnelson.harmonymod.data.HBCollections;

import net.dv8tion.jda.api.interactions.commands.OptionType;

import java.util.ArrayList;

public class Commands {
    public static ArrayList<SlashCommander> slashCommands = new ArrayList<>();
    public static ArrayList<UserContextCommander> userContextCommands = new ArrayList<>();
    public static ArrayList<MessageContextCommander> messageCommands = new ArrayList<>();

    public static void create() {
        createSlashCommands();
        createUserContextCommands();
        createMessageContextCommands();
        combineCommandLists();
    }
    public static void createSlashCommands() {
        HBCollections.slashCommands.add(net.dv8tion.jda.api.interactions.commands.build.Commands.slash("echo", "Replies with the message you send.")
                .addOption(net.dv8tion.jda.api.interactions.commands.OptionType.STRING, "msg", "msg to echo", true)
                .addOption(net.dv8tion.jda.api.interactions.commands.OptionType.CHANNEL, "channelid", "channel to send message to", false));
        HBCollections.slashCommands.add(net.dv8tion.jda.api.interactions.commands.build.Commands.slash("feedback", "Shows help modal."));
        HBCollections.slashCommands.add(net.dv8tion.jda.api.interactions.commands.build.Commands.slash("mod", "Brings up Mod Control Panel")
                .addOption(net.dv8tion.jda.api.interactions.commands.OptionType.MENTIONABLE, "mentionable", "User, Role, or Member.", false));
        HBCollections.slashCommands.add(net.dv8tion.jda.api.interactions.commands.build.Commands.slash("ping", "Calculate the ping of the bot."));
        HBCollections.slashCommands.add(net.dv8tion.jda.api.interactions.commands.build.Commands.slash("prefix", "returns the bot prefix"));
        HBCollections.slashCommands.add(net.dv8tion.jda.api.interactions.commands.build.Commands.slash("purge", "Replies with the message you send.")
                .addOption(net.dv8tion.jda.api.interactions.commands.OptionType.INTEGER, "amount", "Number of messages to purge", true));
        HBCollections.slashCommands.add(net.dv8tion.jda.api.interactions.commands.build.Commands.slash("rolebutton", "Adds a button that gives a role")
                .addOption(net.dv8tion.jda.api.interactions.commands.OptionType.ROLE, "rolebuttonrole", "Role to add", true)
                .addOption(net.dv8tion.jda.api.interactions.commands.OptionType.STRING, "rolebuttondescription", "description", false)
                .addOption(net.dv8tion.jda.api.interactions.commands.OptionType.BOOLEAN, "rolebuttontoggle", "is toggleable?", false));
        HBCollections.slashCommands.add(net.dv8tion.jda.api.interactions.commands.build.Commands.slash("time", "Displays a time conversion table.")
                .addOption(OptionType.BOOLEAN, "ephemeral", "Set false to make visible to everyone."));
        HBCollections.slashCommands.add(net.dv8tion.jda.api.interactions.commands.build.Commands.slash("test", "test generic event processor"));
    }
    public static void createUserContextCommands() {
        HBCollections.userContextCommands.add(net.dv8tion.jda.api.interactions.commands.build.Commands.user("mute"));
    }
    public static void createMessageContextCommands(){
        HBCollections.messageContextCommands.add(net.dv8tion.jda.api.interactions.commands.build.Commands.message("toggleStandardSuccess"));
        HBCollections.messageContextCommands.add(net.dv8tion.jda.api.interactions.commands.build.Commands.message("ReportMessage"));
    }
    public static void combineCommandLists(){
        HBCollections.guildCommands.addAll(HBCollections.slashCommands);
        HBCollections.guildCommands.addAll(HBCollections.userContextCommands);
        HBCollections.guildCommands.addAll(HBCollections.messageContextCommands);
    }

    public static void register(){
        create();
        new SlashEcho();
        new SlashFeedback();
        new SlashMenu();
        new SlashMod();
        new SlashPing();
        new SlashPrefix();
        new SlashPurge();
        new SlashRoleButton();
        new SlashTime();

        new UserContextMute();

        new MessageContextReportMessage();
    }

    public static void registerSlashCommand(SlashCommander slashCommander){
        slashCommands.add(slashCommander);
    }

    public static void registerUserContextCommand(UserContextCommander userContextCommander) {
        userContextCommands.add(userContextCommander);
    }

    public static void registerMessageCommand(MessageContextCommander messageContextCommander){
        messageCommands.add(messageContextCommander);
    }
}
