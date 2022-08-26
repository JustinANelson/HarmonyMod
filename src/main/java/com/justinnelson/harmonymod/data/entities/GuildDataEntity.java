package com.justinnelson.harmonymod.data.entities;

import com.justinnelson.harmonymod.data.entities.helpers.AutoResponse;

import net.dv8tion.jda.api.entities.Guild;

public class GuildDataEntity {
    //Default module
    private String id;
    private String name;
    private String ownerID;
    private boolean online;
    private boolean isPremium;
    private String feedBackCHannel;

    //Logging module
    private String logServerJoinLeftChannel;
    private String logInviteChannel;
    private String logDeleteChannel;
    private String logEditChannel;

    //Managed mute module
    private String muteChannel;
    private String mutedRole;
    private String timeoutChannel;
    private String timeoutRole;

    //Auto responder module
    private AutoResponse[] autoResponses;

    //Moderation module
    private String[] moderatorRoles;
    private String logModerationChannel;
    private String logBanChannel;
    private String logKickChannel;

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
