package com.justinnelson.harmonymod.data.db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.justinnelson.harmonymod.data.AppConfig;
import com.justinnelson.harmonymod.data.HMCollections;
import com.justinnelson.harmonymod.data.db.dto.GuildDataDTO;
import com.justinnelson.harmonymod.data.db.dto.ModLogDTO;
import com.justinnelson.harmonymod.data.db.dto.UserDataDTO;
import com.justinnelson.harmonymod.data.entities.GuildDataEntity;
import com.justinnelson.harmonymod.data.entities.ModLogEntity;
import com.justinnelson.harmonymod.data.entities.UserDataEntity;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoServerException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.ISnowflake;

import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class DB {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    void info(String message){ log.info(message); }
    void error(String message){ log.error(message); }
    void debug(String message){ log.debug(message); }
    void trace(String message){ log.trace(message); }
    void warn(String message){ log.warn(message); }

    public MongoClient dbClient;
    public MongoDatabase dbDatabase;
    public String botDataCollection = "botData";

    private static Gson gsonGuildDataDTO = new GsonBuilder().serializeNulls().create();
    private static Gson gsonUserDataDTO = new GsonBuilder().serializeNulls().create();
    private static Gson gsonNewModLogDTO = new GsonBuilder().serializeNulls().create();

    public DB() {
        dbClient = connect();
        dbDatabase = dbClient.getDatabase(AppConfig.DBNAME);
    }
    public MongoClient connect(){
        // Replace the uri string with your MongoDB deployment's connection string.
        ConnectionString connString = new ConnectionString(AppConfig.DBURI);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connString)
                .applyToSocketSettings(builder ->
                        builder.connectTimeout(10, TimeUnit.SECONDS)
                                .readTimeout(15, TimeUnit.SECONDS))
                .retryWrites(true)
                .build();
        return MongoClients.create(settings);
    }
    public void checkOnlineGuildsExist(JDA jda) {

        //Get list of all online guild IDs from jda cache.
        List<String> onlineGuildIDs = jda.getGuilds().stream().map(ISnowflake::getId).collect(Collectors.toList());

        //Retrieve collection of GuildDataDTOs stored in the db.
        MongoCollection<Document> collection = dbDatabase.getCollection("guilds");

        //Add all guildIDs stored in the DB to a collection.
        List<String> dbGuildIDs = new ArrayList<>();
        for (Document document : collection.find()) {
            dbGuildIDs.add(document.getString("guildID"));
        }

        //Remove all guilds that already have a DB entry.
        onlineGuildIDs.removeAll(dbGuildIDs);

        trace("Found " + onlineGuildIDs.size() + " connected guilds that are not in the DB.");
        int size = onlineGuildIDs.size();
        for (int x = 0; x < size; x++) {
            Guild guild = jda.getGuildById(onlineGuildIDs.get(x));
            GuildDataEntity guildDataEntity =
                    new GuildDataEntity(guild);
            guildDataEntity.setOnline(true);
            HMCollections.cachedGuilds.add(guildDataEntity);
            addGuildDataToDB(guildDataEntity);
        }
    }
    public void checkJoinedGuildExists(Guild guild) {
        MongoCollection<Document> collection = dbDatabase.getCollection("guilds");
        long count = collection.countDocuments(new BsonDocument("guildID", new BsonString(guild.getId())));
        GuildDataEntity guildDataEntity = new GuildDataEntity(guild);
        guildDataEntity.setOnline(true);
        HMCollections.cachedGuilds.add(guildDataEntity);
        if (count > 0) {
            info("Joined existing guild " + guild.getName() + ".");
        } else {
            info("Joined new guild. Creating entry for " + guild.getId() + "/" + guild.getName() + ".");
            //Adds new entry to the DB.
            addGuildDataToDB(guildDataEntity);
        }

        /* For use if DB is in sharded cluster
        Iterator<Document> it = collection.aggregate(Arrays.asList(

                new Document("$match", new Document("code", "abcdefg")),
                new Document("$group", new Document("_id", null).append("count",
                        new Document("$sum", 1))))).iterator();
        int count = it.hasNext() ? (Integer)it.next().get("count") : 0;
        */

    }
    public void addGuildDataToDB(GuildDataEntity guildDataEntity){
        GuildDataDTO guildDataDTO = new GuildDataDTO();
        guildDataDTO.id = guildDataEntity.getId();
        guildDataDTO.name = guildDataEntity.getName();
        guildDataDTO.ownerID = guildDataEntity.getOwnerID();

        String json = gsonGuildDataDTO.toJson(guildDataDTO);
        Document doc = Document.parse(json);
        try {
            dbDatabase.getCollection("guilds").insertOne(doc);
        }
        catch (MongoServerException ex) {
            error(ex.getMessage());
        }
        debug("Entry created for " + guildDataDTO.name + ".");
    }
    public void addUserDataTODB(UserDataEntity userDataEntity) {
        UserDataDTO userDataDTO = new UserDataDTO();
        userDataDTO.id = userDataEntity.getId();
        userDataDTO.nicknames = userDataEntity.getNicknames();
    }
    public void newModLogEntry(ModLogEntity modLogEntity) {

        ModLogDTO modLogDTO = new ModLogDTO();
        modLogDTO.logTime = modLogEntity.getLogTime();
        modLogDTO.moderationID = modLogEntity.getModerationID();
        modLogDTO.guildID = modLogEntity.getGuildID();
        modLogDTO.targetID = modLogEntity.getTargetID();
        modLogDTO.modID = modLogEntity.getModID();;
        modLogDTO.typeOfModeration = modLogEntity.getTypeOfModeration();
        modLogDTO.moderationMessage = modLogEntity.getModerationMessage();

        String json = gsonNewModLogDTO.toJson(modLogDTO);
        Document doc = Document.parse(json);
        try {
            dbDatabase.getCollection("modlogs").insertOne(doc);
        }
        catch (MongoServerException ex) {
            error(ex.getMessage());
        }
        debug("Mod Log Entry created: " + modLogEntity.toString()  + ".");


    }
}
