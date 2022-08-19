package com.justinnelson.harmonymod.data.entities;

import com.justinnelson.harmonymod.core.HarmonyMod;
import com.justinnelson.harmonymod.data.HMCollections;
import com.justinnelson.harmonymod.data.entities.helpers.TypeOfModeration;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;

import java.util.Objects;

public class ModerationEntity {

    public String guildID;
    public String targetID;
    public String actorID;
    public String moderationID;
    public String typeOfModeration;
    public String moderationMessage;

    //Moderation with optional message
    public ModerationEntity(Guild guild, User target, User actor, TypeOfModeration type, String message) {

        boolean exists = false;
        long newID = 0;
        while (!exists) {
            newID = HarmonyMod.botConfig.random.nextLong();
            exists = HMCollections.generatedModerationIDs.add(newID);
        }

        this.moderationID = String.valueOf(newID);
        this.guildID = guild.getId();
        this.targetID = target.getId();
        this.actorID = actor.getId();
        this.typeOfModeration = type.name();
        if (Objects.isNull(message)) {
            this.moderationMessage = "No message submitted.";
        }
        else {
            this.moderationMessage = message;
        }
    }

    //Moderation without a message
    public ModerationEntity(Guild guild, User target, User actor, TypeOfModeration type) {
        new ModerationEntity(guild, target, actor, type, null);
    }

    public String getGuildID() {return guildID;}
    public String getTargetID() {return targetID;}
    public String getActorID() {return actorID;}
    public String getModerationID() {return moderationID;}
    public String getTypeOfModeration() {return typeOfModeration;}
    public String getModerationMessage() {return moderationMessage;}

    public void setGuildID(String guildID) {this.guildID = guildID;}
    public void setTargetID(String targetID) {this.targetID = targetID;}
    public void setActorID(String actorID) {this.actorID = actorID;}
    public void setModerationID(String moderationID) {this.moderationID = moderationID;}
    public void setTypeOfModeration(String typeOfModeration) {this.typeOfModeration = typeOfModeration;}
    public void setModerationMessage(String moderationMessage) {this.moderationMessage = moderationMessage;}

    @Override
    public String toString() {
        return "ModerationEntity{" +
                "guildID='" + guildID + '\'' +
                ", targetID='" + targetID + '\'' +
                ", actorID='" + actorID + '\'' +
                ", moderationID='" + moderationID + '\'' +
                ", typeOfModeration='" + typeOfModeration + '\'' +
                ", moderationMessage='" + moderationMessage + '\'' +
                '}';
    }
}
