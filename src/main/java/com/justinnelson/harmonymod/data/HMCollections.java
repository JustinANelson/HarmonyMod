package com.justinnelson.harmonymod.data;

import com.justinnelson.harmonymod.data.entities.GuildDataEntity;
import com.justinnelson.harmonymod.interactions.commands.events.customevents.ModerationEvent;

import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.util.ArrayList;
import java.util.HashSet;

public class HMCollections {

    //COMMANDS
    public static ArrayList<SlashCommandData> slashCommands = new ArrayList<>();
    public static ArrayList<CommandData> userContextCommands = new ArrayList<>();
    public static ArrayList<CommandData> messageContextCommands = new ArrayList<>();
    public static ArrayList<CommandData> messageReceiveCommands = new ArrayList<>();
    public static ArrayList<CommandData> guildCommands = new ArrayList<>();

    //EVENTS
    public static ArrayList<Button> buttonRoles = new ArrayList<>();
    public static ArrayList<ModerationEvent> moderationEvents = new ArrayList<>();

    //BOT CACHE
    public static ArrayList<GuildDataEntity> cachedGuilds = new ArrayList<>();

    public static HashSet<Long> generatedModerationIDs = new HashSet<>();
}
