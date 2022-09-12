package com.justinnelson.harmonymod.data.db;

import static com.mongodb.client.model.Filters.eq;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.justinnelson.harmonymod.AppConfig;
import com.justinnelson.harmonymod.data.HMCollections;
import com.justinnelson.harmonymod.data.db.dto.GuildDataDTO;
import com.justinnelson.harmonymod.data.db.dto.ModLogDTO;
import com.justinnelson.harmonymod.data.db.dto.UserDTO;
import com.justinnelson.harmonymod.data.entities.GuildDataEntity;
import com.justinnelson.harmonymod.data.entities.ModLogEntity;
import com.justinnelson.harmonymod.data.entities.UserEntity;
import com.justinnelson.harmonymod.data.entities.helpers.MutedMember;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoServerException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.ISnowflake;
import net.dv8tion.jda.api.entities.Member;

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
    public Mappers mapper = new Mappers();

    private static Gson gson = new GsonBuilder().serializeNulls().create();

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
    public void checkOnlineGuildsExists(JDA jda) {

        //Get list of all online guild IDs from jda cache.
        List<String> onlineGuildIDs = jda.getGuilds().stream().map(ISnowflake::getId).collect(Collectors.toList());

        //Retrieve collection of GuildDataDTOs stored in the db.
        MongoCollection<Document> collection = dbDatabase.getCollection("guilds");

        //Add all guildIDs stored in the DB to a collection.
        List<String> dbGuildIDs = new ArrayList<>();
        for (Document document : collection.find()) {
            dbGuildIDs.add(document.getString("id"));
        }

        //Remove all guilds that already have a DB entry.
        onlineGuildIDs.removeAll(dbGuildIDs);

        trace("Found " + onlineGuildIDs.size() + " connected guilds that are not in the DB.");

        //Create new guildData entry for guilds not in the DB but are connected.
        //This should never occur unless the DB was offline when the bot was added.
        int size = onlineGuildIDs.size();
        for (int x = 0; x < size; x++) {
            Guild guild = jda.getGuildById(onlineGuildIDs.get(x));
            GuildDataEntity guildDataEntity =
                    new GuildDataEntity(guild);
            guildDataEntity.setOnline(true);
            HMCollections.cachedGuilds.add(guildDataEntity);
            addGuildData(guildDataEntity);
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
            addGuildData(guildDataEntity);
        }

        /* For use if DB is in sharded cluster
        Iterator<Document> it = collection.aggregate(Arrays.asList(

                new Document("$match", new Document("code", "abcdefg")),
                new Document("$group", new Document("_id", null).append("count",
                        new Document("$sum", 1))))).iterator();
        int count = it.hasNext() ? (Integer)it.next().get("count") : 0;
        */

    }
    public void addGuildData(GuildDataEntity guildDataEntity){
        GuildDataDTO guildDataDTO = new GuildDataDTO();
        guildDataDTO.id = guildDataEntity.getId();
        guildDataDTO.name = guildDataEntity.getName();
        guildDataDTO.ownerID = guildDataEntity.getOwnerID();
        guildDataDTO.caseID = guildDataEntity.getCaseID();

        String json = gson.toJson(guildDataDTO);
        Document doc = Document.parse(json);
        try {
            dbDatabase.getCollection("guilds").insertOne(doc);
        }
        catch (MongoServerException ex) {
            error(ex.getMessage());
        }
        debug("Entry created for " + guildDataDTO.name + ".");
    }

    public boolean checkUserExists(Member member) {
        MongoCollection<Document> collection = dbDatabase.getCollection("users");
        long count = collection.countDocuments(new BsonDocument("id", new BsonString(member.getId())));
        return count > 0;
    }
    public void addUser(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.id = userEntity.getId();
        userDTO.timezone = userEntity.getTimezone();
        userDTO.nicknames = userEntity.getNicknames();
        userDTO.usernames = userEntity.getUsernames();
        userDTO.mutedMember = userEntity.getMutedMemberEntities();

        String json = gson.toJson(userDTO);
        Document doc = Document.parse(json);
        try {
            dbDatabase.getCollection("users").insertOne(doc);
        }
        catch (MongoServerException ex) {
            error(ex.getMessage());
        }
    }

    public void addModLogEntry(ModLogEntity modLogEntity) {

        ModLogDTO modLogDTO = new ModLogDTO();
        modLogDTO.logTime = modLogEntity.getLogTime();
        modLogDTO.moderationID = modLogEntity.getModerationID();
        modLogDTO.guildID = modLogEntity.getGuildID();
        modLogDTO.guildName = modLogEntity.getGuildName();
        modLogDTO.caseID = modLogEntity.getCaseID();
        modLogDTO.targetID = modLogEntity.getTargetID();
        modLogDTO.targetName = modLogEntity.getTargetName();
        modLogDTO.modID = modLogEntity.getModID();
        modLogDTO.modName = modLogEntity.getModName();
        modLogDTO.typeOfModeration = modLogEntity.getTypeOfModeration();
        modLogDTO.moderationMessage = modLogEntity.getModerationMessage();

        String json = gson.toJson(modLogDTO);
        Document doc = Document.parse(json);
        try {
            dbDatabase.getCollection("modlogs").insertOne(doc);
        }
        catch (MongoServerException ex) {
            error(ex.getMessage());
        }

        //Update guild caseID total
        updateGuildIntValue(modLogEntity.getGuildID(), "caseID", modLogDTO.caseID);

        debug("Mod Log Entry created: " + modLogEntity  + ".");

    }
    public ArrayList<ModLogEntity> getMemberModLogs(String guildID, String memberID) {
        MongoCollection<Document> collection = dbDatabase.getCollection("modlogs");
        FindIterable<Document> fi = collection.find();

        ArrayList<ModLogDTO> modLogDTOS = new ArrayList<>();
        ArrayList<ModLogEntity> modLogEntities = new ArrayList<>();

        try {
            for (Document doc : fi) {
                if(doc.get("guildID").equals(guildID)) {
                    String found = doc.getString("targetID");
                    if(memberID.equals(found)) {
                        modLogDTOS.add(gson.fromJson(doc.toJson(), ModLogDTO.class));
                    }
                }
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }

        int size = modLogDTOS.size();
        trace("Logs found:" + size);
        for (int x = 0; x < size; x++) {
            ModLogEntity modLogEntity = new ModLogEntity();
            modLogEntity.setLogTime(modLogDTOS.get(x).logTime);
            modLogEntity.setGuildName(modLogDTOS.get(x).guildName);
            modLogEntity.setCaseID(modLogDTOS.get(x).caseID);
            modLogEntity.setModID(modLogDTOS.get(x).modID);
            modLogEntity.setModName(modLogDTOS.get(x).modName);
            modLogEntity.setTypeOfModeration(modLogDTOS.get(x).typeOfModeration);
            modLogEntity.setModerationMessage(modLogDTOS.get(x).moderationMessage);
            modLogEntities.add(modLogEntity);
        }
        return modLogEntities;
    }

    public void addMutedMember(MutedMember member, String userID) {
        Document doc = dbDatabase.getCollection("users").find(eq("id", userID)).first();

        UserDTO userDTO = gson.fromJson(doc.toJson(), UserDTO.class);
        userDTO.mutedMember.add(member);

        Document doc2 = Document.parse(gson.toJson(userDTO));
        try {
            dbDatabase.getCollection("users").replaceOne(Filters.eq("id", userID), doc2);
        }
        catch (MongoServerException ex) {
            error(ex.getMessage());
        }
    }
    public void removeMutedMember(Member member) {
        String guildID = member.getGuild().getId();

        Document doc = dbDatabase.getCollection("users").find(eq("id", member.getId())).first();

        //Created a new userDTO object from the returned doc
        UserDTO userDTO = gson.fromJson(doc.toJson(), UserDTO.class);

        //Find the muted member object in the user doc
        userDTO.mutedMember.forEach(System.out::println);
        MutedMember mutedMember = userDTO.mutedMember.stream()
                .filter(guild -> guild.getGuildID().contains(guildID))
                .findFirst()
                .orElse(null);

        //Remove the muted member object
        userDTO.mutedMember.remove(mutedMember);

        Document doc2 = Document.parse(gson.toJson(userDTO));
        try {
            dbDatabase.getCollection("users").replaceOne(Filters.eq("id", member.getId()), doc2);
        }
        catch (MongoServerException ex) {
            error(ex.getMessage());
        }
    }
    public MutedMember getMutedMember(Member member) {
        String guildID = member.getGuild().getId();

        Document doc = dbDatabase.getCollection("users").find(eq("id", member.getId())).first();
        String json = doc.toJson();

        UserDTO userDTO = gson.fromJson(json, UserDTO.class);

        return userDTO.mutedMember.stream()
                        .filter(guild -> guild.getGuildID().contains(guildID)).findFirst().orElse(null);
    }
    public void updateMutedMember(MutedMember mutedMember, Member member, boolean addRemove) {
        if (addRemove) {
            Document doc = dbDatabase.getCollection("users").find(eq("id", member.getId())).first();

            UserDTO userDTO = gson.fromJson(doc.toJson(), UserDTO.class);
            userDTO.mutedMember.add(mutedMember);

            Document doc2 = Document.parse(gson.toJson(userDTO));
            try {
                dbDatabase.getCollection("users").replaceOne(Filters.eq("id", member.getId()), doc2);
            }
            catch (MongoServerException ex) {
                error(ex.getMessage());
            }
        } else {
            String guildID = member.getGuild().getId();

            Document doc = dbDatabase.getCollection("users").find(eq("id", member.getId())).first();

            //Created a new userDTO object from the returned doc
            UserDTO userDTO = gson.fromJson(doc.toJson(), UserDTO.class);

            //Find the muted member object in the user doc
            userDTO.mutedMember.forEach(System.out::println);
            MutedMember findMutedMember = userDTO.mutedMember.stream()
                    .filter(guild -> guild.getGuildID().contains(guildID))
                    .findFirst()
                    .orElse(null);

            //Remove the muted member object
            userDTO.mutedMember.remove(findMutedMember);

            Document doc2 = Document.parse(gson.toJson(userDTO));
            try {
                dbDatabase.getCollection("users").replaceOne(Filters.eq("id", member.getId()), doc2);
            }
            catch (MongoServerException ex) {
                error(ex.getMessage());
            }
        }
    }

    public String getGuildStringValue(String guildID, String field) {

        Document doc = dbDatabase.getCollection("guilds").find(eq("id", guildID)).first();

        return doc.getString(field);
    }
    public boolean getGuildBoolValue(String guildID, String field) {

        Document doc = dbDatabase.getCollection("guilds").find(eq("id", guildID)).first();

        return doc.getBoolean(field);
    }
    public int getGuildIntValue(String guildID, String field) {
        Document doc = dbDatabase.getCollection("guilds").find(eq("id", guildID)).first();
        return doc.getInteger(field);
    }
    public void updateGuildIntValue(String guildID, String field, int value){
        UpdateResult success =
        dbDatabase.getCollection("guilds").updateOne(eq("id", guildID), Updates.set(field, value));
        if (success.getModifiedCount() > 0) {
            trace("Updated total modlog count to = " + value);
        }
        else {
            trace("Failed to update modlog count");
        }
    }
    public void updateGuildStringValue(String guildID, String field, String value){
        UpdateResult success =
                dbDatabase.getCollection("guilds").updateOne(eq("id", guildID), Updates.set(field, value));
        if (success.getModifiedCount() > 0) {
            trace("Updated " + field + " to " + value);
        }
        else {
            trace("Failed to update " + field + " to " + value);
        }
    }
}