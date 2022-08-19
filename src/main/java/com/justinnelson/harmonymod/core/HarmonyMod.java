package com.justinnelson.harmonymod.core;

import com.justinnelson.harmonymod.commands.commandprocessors.CommandProcessor;
import com.justinnelson.harmonymod.commands.Commands;
import com.justinnelson.harmonymod.data.AppConfig;
import com.justinnelson.harmonymod.data.BotConfig;
import com.justinnelson.harmonymod.db.DB;
import com.justinnelson.harmonymod.events.ModalEventProcessor;
import com.justinnelson.harmonymod.utility.Util;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumSet;

public class HarmonyMod {
    private static final Logger log = LoggerFactory.getLogger(HarmonyMod.class);
    private static void info(String message) { log.info(message); }
    public static JDA jda;
    public static DB db;
    public static BotConfig botConfig;
    public HarmonyMod(){ super(); }
    public static CommandProcessor commandProcessor;
    public static ModalEventProcessor modalEventProcessor;

    public static void main(String[] args) throws Exception {
        jda = JDABuilder.create(AppConfig.TOKEN, EnumSet.allOf(GatewayIntent.class))
                .disableCache(CacheFlag.ACTIVITY)
                .disableIntents(GatewayIntent.DIRECT_MESSAGE_TYPING, GatewayIntent.DIRECT_MESSAGE_TYPING)
                .setLargeThreshold(100)
                .useSharding(0, 1)
                .addEventListeners(new Listeners()).build();
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.playing("Loading..."));
        jda.awaitReady();
        start();
    }
    public static void start() {
        db = new DB();
        botConfig = new BotConfig();
        commandProcessor = new CommandProcessor();
        modalEventProcessor = new ModalEventProcessor();

        Commands.register();
        Util.registerTestGuildParameters();
        Util.registerGlobalCommands();

        info("Ready");
        jda.getPresence().setActivity(Activity.playing("Harmonizing"));
        PingLoop loop = new PingLoop();
        Thread thread = new Thread(loop, "PingUpdate");
        thread.start();
    }
}
