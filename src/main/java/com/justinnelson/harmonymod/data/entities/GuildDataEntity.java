package com.justinnelson.harmonymod.data.entities;

import net.dv8tion.jda.api.entities.Guild;

public class GuildDataEntity {
    String id;
    String name;
    String ownerID;
    boolean online;

    public GuildDataEntity(Guild guild) {
        this.id = guild.getId();
        this.name = guild.getName();
        this.ownerID = guild.getOwnerId();
    }

    public String getId() {return id;}
    public String getName() {return name;}
    public String getOwnerID() {return ownerID;}
    public boolean getOnline() {return online;}

    public void setId(String id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setOwnerID(String ownerID) {this.ownerID = ownerID;}
    public void setOnline(boolean online) {this.online = online;}

    @Override
    public String toString() {
        return "GuildDataEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ownerID='" + ownerID + '\'' +
                ", online=" + online +
                '}';
    }
}
