package com.justinnelson.harmonymod.db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.justinnelson.harmonymod.core.HarmonyMod;
import com.justinnelson.harmonymod.core.Listeners;
import com.justinnelson.harmonymod.data.AppConfig;
import com.justinnelson.harmonymod.db.dto.GuildDataDTO;
import com.justinnelson.harmonymod.entities.GuildEntity;
import com.mongodb.Block;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoServerException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import net.dv8tion.jda.api.entities.Guild;

import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class DB {
    private static final Logger log = LoggerFactory.getLogger(Listeners.class);
    void info(String message){ log.info(message); }
    void error(String message){ log.error(message); }
    void debug(String message){ log.debug(message); }
    void trace(String message){ log.trace(message); }
    void warn(String message){ log.warn(message); }

    public MongoClient dbClient;
    public MongoDatabase dbDatabase;
    public String botDataCollection = "botData";

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

    private static Gson gsonAddGuildToDB = new GsonBuilder()
            .serializeNulls()
            .create();

    public boolean checkGuildExists(Guild guild) {
        boolean isFound = false;
        MongoCollection<Document> collection = dbDatabase.getCollection("guilds");
        long count = collection.countDocuments(new BsonDocument("guildID", new BsonString(guild.getId())));
        if (count > 0) {
            isFound = true;
            info("Joined existing guild " + guild.getName() + ".");
        } else {
            info("Joined new guild. Creating entry for " + guild.getId() + "/" + guild.getName() + ".");
            addGuildToDB(guild.getId(), guild.getName());
        }

        /* For use if DB is in sharded cluster
        Iterator<Document> it = collection.aggregate(Arrays.asList(

                new Document("$match", new Document("code", "abcdefg")),
                new Document("$group", new Document("_id", null).append("count",
                        new Document("$sum", 1))))).iterator();
        int count = it.hasNext() ? (Integer)it.next().get("count") : 0;
        */

        return isFound;
    }
    public void addGuildToDB(String id, String name){
        GuildDataDTO guildDataDTO = new GuildDataDTO();
        guildDataDTO.guildID = id;
        guildDataDTO.guildName = name;

        String json = gsonAddGuildToDB.toJson(guildDataDTO);
        Document doc = Document.parse(json);
        try {
            dbDatabase.getCollection("guilds").insertOne(doc);
        }
        catch (MongoServerException ex) {
            error(ex.getMessage());
        }
        info("Entry created for " + guildDataDTO.guildName + ".");
    }
}
