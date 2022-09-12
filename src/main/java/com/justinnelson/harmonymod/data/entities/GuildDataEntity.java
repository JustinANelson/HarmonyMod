package com.justinnelson.harmonymod.data.entities;

import com.justinnelson.harmonymod.data.entities.helpers.AutoResponse;

import net.dv8tion.jda.api.entities.Guild;

import java.util.Arrays;

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
    private int caseID;

    //Managed mute module
    private String muteChannel;
    private String mutedRole;
    private String timeoutChannel;
    private String timeoutRole;
    private boolean keepRolesOnMute;

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

    public GuildDataEntity() {

    }

    public void setId(String id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setOwnerID(String ownerID) {this.ownerID = ownerID;}
    public void setOnline(boolean online) {this.online = online;}
    public void setPremium(boolean premium) {isPremium = premium;}
    public void setFeedBackCHannel(String feedBackCHannel) {this.feedBackCHannel = feedBackCHannel;}
    public void setLogServerJoinLeftChannel(String logServerJoinLeftChannel) {this.logServerJoinLeftChannel = logServerJoinLeftChannel;}
    public void setLogInviteChannel(String logInviteChannel) {this.logInviteChannel = logInviteChannel;}
    public void setLogDeleteChannel(String logDeleteChannel) {this.logDeleteChannel = logDeleteChannel;}
    public void setLogEditChannel(String logEditChannel) {this.logEditChannel = logEditChannel;}
    public void setCaseID(int caseID) {this.caseID = caseID;}
    public void setMuteChannel(String muteChannel) {this.muteChannel = muteChannel;}
    public void setMutedRole(String mutedRole) {this.mutedRole = mutedRole;}
    public void setTimeoutChannel(String timeoutChannel) {this.timeoutChannel = timeoutChannel;}
    public void setTimeoutRole(String timeoutRole) {this.timeoutRole = timeoutRole;}
    public void setKeepRolesOnMute(boolean keepRolesOnMute) {this.keepRolesOnMute = keepRolesOnMute;}
    public void setAutoResponses(AutoResponse[] autoResponses) {this.autoResponses = autoResponses;}
    public void setModeratorRoles(String[] moderatorRoles) {this.moderatorRoles = moderatorRoles;}
    public void setLogModerationChannel(String logModerationChannel) {this.logModerationChannel = logModerationChannel;}
    public void setLogBanChannel(String logBanChannel) {this.logBanChannel = logBanChannel;}
    public void setLogKickChannel(String logKickChannel) {this.logKickChannel = logKickChannel;}

    public String getId() {return id;}
    public String getName() {return name;}
    public String getOwnerID() {return ownerID;}
    public boolean getOnline() {return online;}
    public boolean getPremium() {return isPremium;}
    public String getFeedBackCHannel() {return feedBackCHannel;}
    public String getLogServerJoinLeftChannel() {return logServerJoinLeftChannel;}
    public String getLogInviteChannel() {return logInviteChannel;}
    public String getLogDeleteChannel() {return logDeleteChannel;}
    public String getLogEditChannel() {return logEditChannel;}
    public int getCaseID() {return caseID;}
    public String getMuteChannel() {return muteChannel;}
    public String getMutedRole() {return mutedRole;}
    public String getTimeoutChannel() {return timeoutChannel;}
    public String getTimeoutRole() {return timeoutRole;}
    public boolean getKeepRolesOnMute() {return keepRolesOnMute;}
    public AutoResponse[] getAutoResponses() {return autoResponses;}
    public String[] getModeratorRoles() {return moderatorRoles;}
    public String getLogModerationChannel() {return logModerationChannel;}
    public String getLogBanChannel() {return logBanChannel;}
    public String getLogKickChannel() {return logKickChannel;}

    @Override
    public String toString() {
        return "GuildDataEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", ownerID='" + ownerID + '\'' +
                ", online=" + online +
                ", isPremium=" + isPremium +
                ", feedBackCHannel='" + feedBackCHannel + '\'' +
                ", logServerJoinLeftChannel='" + logServerJoinLeftChannel + '\'' +
                ", logInviteChannel='" + logInviteChannel + '\'' +
                ", logDeleteChannel='" + logDeleteChannel + '\'' +
                ", logEditChannel='" + logEditChannel + '\'' +
                ", caseID='" + caseID + '\'' +
                ", muteChannel='" + muteChannel + '\'' +
                ", mutedRole='" + mutedRole + '\'' +
                ", timeoutChannel='" + timeoutChannel + '\'' +
                ", timeoutRole='" + timeoutRole + '\'' +
                ", keepRolesOnMute=" + keepRolesOnMute +
                ", autoResponses=" + Arrays.toString(autoResponses) +
                ", moderatorRoles=" + Arrays.toString(moderatorRoles) +
                ", logModerationChannel='" + logModerationChannel + '\'' +
                ", logBanChannel='" + logBanChannel + '\'' +
                ", logKickChannel='" + logKickChannel + '\'' +
                '}';
    }
}
