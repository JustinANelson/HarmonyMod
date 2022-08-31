package com.justinnelson.harmonymod.modules.interactions.commands;

import com.justinnelson.harmonymod.data.HMCollections;

import net.dv8tion.jda.api.interactions.commands.OptionType;

public class Commands {

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
        HMCollections.slashCommands.add(net.dv8tion.jda.api.interactions.commands.build.Commands.slash("test", "test generic event processor"));
        HMCollections.slashCommands.add(net.dv8tion.jda.api.interactions.commands.build.Commands.slash("time", "Displays a time conversion table.")
                .addOption(OptionType.BOOLEAN, "ephemeral", "Set false to make visible to everyone."));

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

}
