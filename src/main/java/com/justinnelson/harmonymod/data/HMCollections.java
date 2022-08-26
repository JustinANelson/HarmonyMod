package com.justinnelson.harmonymod.data;

import com.justinnelson.harmonymod.data.entities.GuildDataEntity;
import com.justinnelson.harmonymod.interactions.events.customevents.ModerationInteractionEvent;

import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.util.ArrayList;

public class HMCollections {

    //SlashCommands
    public static ArrayList<String> slashCommandNames = new ArrayList<>();

    //COMMANDS
    public static ArrayList<SlashCommandData> slashCommands = new ArrayList<>();
    public static ArrayList<CommandData> userContextCommands = new ArrayList<>();
    public static ArrayList<CommandData> messageContextCommands = new ArrayList<>();
    public static ArrayList<CommandData> messageReceiveCommands = new ArrayList<>();
    public static ArrayList<CommandData> guildCommands = new ArrayList<>();

    //EVENTS
    public static ArrayList<Button> buttonRoles = new ArrayList<>();
    public static ArrayList<ModerationInteractionEvent> moderationInteractionEvents = new ArrayList<>();

    //BOT CACHE
    public static ArrayList<GuildDataEntity> cachedGuilds = new ArrayList<>();

}
