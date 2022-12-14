package com.justinnelson.harmonymod;

import com.justinnelson.harmonymod.core.MyListenerAdapter;
import com.justinnelson.harmonymod.data.BotConfig;
import com.justinnelson.harmonymod.data.db.DB;
import com.justinnelson.harmonymod.modules.interactions.commands.commandprocessors.CommandProcessor;
import com.justinnelson.harmonymod.modules.interactions.events.eventprocessors.EventProcessor;
import com.justinnelson.harmonymod.utility.PingUpdates;
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
    public HarmonyMod(){ super(); }
    private static final Logger log = LoggerFactory.getLogger(HarmonyMod.class);
    private static void info(String message) { log.info(message); }
    public static JDA jda;
    public static DB db;
    public static BotConfig botConfig;
    public static CommandProcessor commandProcessor;
    public static EventProcessor eventProcessor;

    public static void main(String[] args) throws Exception {
        info("Starting JDA");
        jda = JDABuilder.create(AppConfig.TOKEN, EnumSet.allOf(GatewayIntent.class))
                .disableCache(CacheFlag.ACTIVITY)
                .disableIntents(GatewayIntent.DIRECT_MESSAGE_TYPING, GatewayIntent.DIRECT_MESSAGE_TYPING)
                .setLargeThreshold(100)
                .useSharding(0, 1)
                .addEventListeners(new MyListenerAdapter()).build();
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
        jda.getPresence().setActivity(Activity.playing("Loading..."));
        jda.awaitReady();
        start();
    }
    public static void start() {
        info("BOT NAME: " + jda.getSelfUser().getName());
        info("BOT ID: " + jda.getSelfUser().getId());
        db = new DB();
        botConfig = new BotConfig();
        commandProcessor = new CommandProcessor();
        eventProcessor = new EventProcessor();

        //Sync database and jda cache connected guilds
        db.checkOnlineGuildsExists(jda);

        //Uncomment only when new commands are added.
        //Util.registerTestGuildParameters();
        //Util.registerGlobalCommands();

        jda.getPresence().setActivity(Activity.playing("Harmonizing"));
        if (log.isTraceEnabled()) {
            PingUpdates loop = new PingUpdates();
            Thread thread = new Thread(loop, "PingUpdate");
            thread.start();
        }
        info("Ready");
    }
}
