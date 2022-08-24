package com.justinnelson.harmonymod.data.entities;

import com.justinnelson.harmonymod.data.entities.helpers.TypeOfModeration;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

public class ModLogEntity {

    public String logTime;
    public String guildID;
    public String targetID;
    public String modID;
    public UUID moderationID;
    public String typeOfModeration;
    public String moderationMessage;

    //Moderation with optional message
    public ModLogEntity(Guild guild, Member target, Member mod, TypeOfModeration type, String message) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss.SSSSSS Z");
        this.logTime = ZonedDateTime.now().format(formatter);
        this.moderationID = UUID.randomUUID();
        this.guildID = guild.getId();
        this.targetID = target.getId();
        this.modID = mod.getId();
        this.typeOfModeration = type.name();
        if (Objects.isNull(message)) {
            this.moderationMessage = "No message submitted.";
        }
        else {
            this.moderationMessage = message;
        }
    }

    //Moderation without a message
    public ModLogEntity(Guild guild, Member target, Member actor, TypeOfModeration type) {
        new ModLogEntity(guild, target, actor, type, null);
    }

    public String getLogTime() {return logTime;}
    public String getGuildID() {return guildID;}
    public String getTargetID() {return targetID;}
    public String getModID() {return modID;}
    public UUID getModerationID() {return moderationID;}
    public String getTypeOfModeration() {return typeOfModeration;}
    public String getModerationMessage() {return moderationMessage;}

    public void setLogTime(String logTime) {this.logTime = logTime;}
    public void setGuildID(String guildID) {this.guildID = guildID;}
    public void setTargetID(String targetID) {this.targetID = targetID;}
    public void setModID(String actorID) {this.modID = modID;}
    public void setModerationID(UUID moderationID) {this.moderationID = moderationID;}
    public void setTypeOfModeration(String typeOfModeration) {this.typeOfModeration = typeOfModeration;}
    public void setModerationMessage(String moderationMessage) {this.moderationMessage = moderationMessage;}

    @Override
    public String toString() {
        return "ModerationEntity{" +
                "guildID='" + guildID + '\'' +
                ", targetID='" + targetID + '\'' +
                ", modID='" + modID + '\'' +
                ", moderationID='" + moderationID.toString() + '\'' +
                ", typeOfModeration='" + typeOfModeration + '\'' +
                ", moderationMessage='" + moderationMessage + '\'' +
                '}';
    }
}
