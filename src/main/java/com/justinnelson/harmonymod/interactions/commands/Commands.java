package com.justinnelson.harmonymod.interactions.commands;

import com.justinnelson.harmonymod.interactions.commands.commandprocessors.MessageContextCommander;
import com.justinnelson.harmonymod.interactions.commands.commandprocessors.MessageReceivedCommander;
import com.justinnelson.harmonymod.interactions.commands.commandprocessors.UserContextCommander;
import com.justinnelson.harmonymod.interactions.commands.commandprocessors.SlashCommander;
import com.justinnelson.harmonymod.interactions.commands.usercontext.UserContextMute;
import com.justinnelson.harmonymod.interactions.commands.messagecontext.MessageContextReportMessage;
import com.justinnelson.harmonymod.interactions.commands.messagereceived.MessageReceivedPing;
import com.justinnelson.harmonymod.interactions.commands.messagereceived.MessageReceivedShutdown;
import com.justinnelson.harmonymod.interactions.commands.slash.SlashEcho;
import com.justinnelson.harmonymod.interactions.commands.slash.SlashFeedback;
import com.justinnelson.harmonymod.interactions.commands.slash.SlashMenu;
import com.justinnelson.harmonymod.interactions.commands.slash.SlashMod;
import com.justinnelson.harmonymod.interactions.commands.slash.SlashPing;
import com.justinnelson.harmonymod.interactions.commands.slash.SlashPrefix;
import com.justinnelson.harmonymod.interactions.commands.slash.SlashPurge;
import com.justinnelson.harmonymod.interactions.commands.slash.SlashRoleButton;
import com.justinnelson.harmonymod.interactions.commands.slash.SlashTime;
import com.justinnelson.harmonymod.data.HMCollections;

import net.dv8tion.jda.api.interactions.commands.OptionType;

import java.util.ArrayList;

public class Commands {
    public static ArrayList<SlashCommander> slashCommands = new ArrayList<>();
    public static ArrayList<UserContextCommander> userContextCommands = new ArrayList<>();
    public static ArrayList<MessageContextCommander> messageContextCommands = new ArrayList<>();
    public static ArrayList<MessageReceivedCommander> messageReceivedCommands = new ArrayList<>();

    public static void create() {
        createSlashCommands();
        createUserContextCommands();
        createMessageContextCommands();
        combineCommandLists();
    }
    public static void createSlashCommands() {
        HMCollections.slashCommands.add(net.dv8tion.jda.api.interactions.commands.build.Commands.slash("echo", "Replies with the message you send.")
                .addOption(net.dv8tion.jda.api.interactions.commands.OptionType.STRING, "msg", "msg to echo", true)
                .addOption(net.dv8tion.jda.api.interactions.commands.OptionType.CHANNEL, "channelid", "channel to send message to", false));
        HMCollections.slashCommands.add(net.dv8tion.jda.api.interactions.commands.build.Commands.slash("feedback", "Shows help modal."));
        HMCollections.slashCommands.add(net.dv8tion.jda.api.interactions.commands.build.Commands.slash("mod", "Brings up Mod Control Panel")
                .addOption(net.dv8tion.jda.api.interactions.commands.OptionType.MENTIONABLE, "mentionable", "User, Role, or Member.", false));
        HMCollections.slashCommands.add(net.dv8tion.jda.api.interactions.commands.build.Commands.slash("ping", "Calculate the ping of the bot."));
        HMCollections.slashCommands.add(net.dv8tion.jda.api.interactions.commands.build.Commands.slash("prefix", "returns the bot prefix"));
        HMCollections.slashCommands.add(net.dv8tion.jda.api.interactions.commands.build.Commands.slash("purge", "Replies with the message you send.")
                .addOption(net.dv8tion.jda.api.interactions.commands.OptionType.INTEGER, "amount", "Number of messages to purge", true));
        HMCollections.slashCommands.add(net.dv8tion.jda.api.interactions.commands.build.Commands.slash("rolebutton", "Adds a button that gives a role")
                .addOption(net.dv8tion.jda.api.interactions.commands.OptionType.ROLE, "rolebuttonrole", "Role to add", true)
                .addOption(net.dv8tion.jda.api.interactions.commands.OptionType.STRING, "rolebuttondescription", "description", false)
                .addOption(net.dv8tion.jda.api.interactions.commands.OptionType.BOOLEAN, "rolebuttontoggle", "is toggleable?", false));
        HMCollections.slashCommands.add(net.dv8tion.jda.api.interactions.commands.build.Commands.slash("time", "Displays a time conversion table.")
                .addOption(OptionType.BOOLEAN, "ephemeral", "Set false to make visible to everyone."));
        HMCollections.slashCommands.add(net.dv8tion.jda.api.interactions.commands.build.Commands.slash("test", "test generic event processor"));
    }
    public static void createUserContextCommands() {
        HMCollections.userContextCommands.add(net.dv8tion.jda.api.interactions.commands.build.Commands.user("mute"));
    }
    public static void createMessageContextCommands(){
        HMCollections.messageContextCommands.add(net.dv8tion.jda.api.interactions.commands.build.Commands.message("toggleStandardSuccess"));
        HMCollections.messageContextCommands.add(net.dv8tion.jda.api.interactions.commands.build.Commands.message("ReportMessage"));
    }
    public static void combineCommandLists(){
        HMCollections.guildCommands.addAll(HMCollections.slashCommands);
        HMCollections.guildCommands.addAll(HMCollections.userContextCommands);
        HMCollections.guildCommands.addAll(HMCollections.messageContextCommands);
        HMCollections.guildCommands.addAll(HMCollections.messageReceiveCommands);
    }

    public static void register(){
        create();

        //Slash Commands
        new SlashEcho();
        new SlashFeedback();
        new SlashMenu();
        new SlashMod();
        new SlashPing();
        new SlashPrefix();
        new SlashPurge();
        new SlashRoleButton();
        new SlashTime();

        //User Context Commands
        new UserContextMute();

        //Message Context Commands
        new MessageContextReportMessage();

        //Message Received Commands
        new MessageReceivedPing();
        new MessageReceivedShutdown();
    }

    public static void registerSlashCommand(SlashCommander slashCommander){
        slashCommands.add(slashCommander);
    }

    public static void registerUserContextCommand(UserContextCommander userContextCommander) {
        userContextCommands.add(userContextCommander);
    }

    public static void registerMessageCommand(MessageContextCommander messageContextCommander){
        messageContextCommands.add(messageContextCommander);
    }
    public static void registerMessageReceivedCommand(MessageReceivedCommander messageReceivedCommander) {
        messageReceivedCommands.add(messageReceivedCommander);
    }
}
