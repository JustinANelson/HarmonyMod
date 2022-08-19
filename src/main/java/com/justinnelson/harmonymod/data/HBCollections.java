package com.justinnelson.harmonymod.data;

import com.justinnelson.harmonymod.events.Moderation;
import com.justinnelson.harmonymod.events.RoleButton;

import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.ArrayList;

public class HBCollections {
    public static ArrayList<Moderation> moderations = new ArrayList<>();

    public static ArrayList<RoleButton> roleButtons = new ArrayList<>();

    public static ArrayList<SlashCommandData> slashCommands = new ArrayList<>();
    public static ArrayList<CommandData> userContextCommands = new ArrayList<>();
    public static ArrayList<CommandData> messageContextCommands = new ArrayList<>();
    public static ArrayList<CommandData> guildCommands = new ArrayList<>();
}
