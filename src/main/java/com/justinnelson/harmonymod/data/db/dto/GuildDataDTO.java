package com.justinnelson.harmonymod.data.db.dto;

import com.justinnelson.harmonymod.data.entities.helpers.AutoResponse;

public class GuildDataDTO {

    //Default module
    public String id;
    public String name;
    public String ownerID;
    public boolean online;
    public boolean isPremium;
    public String feedbackChannel;

    //Logging module
    public String logServerJoinLeftChannel;
    public String logInviteChannel;
    public String logDeleteChannel;
    public String logEditChannel;

    //Managed mute module
    public String muteChannel;
    public String mutedRole;
    public String timeoutChannel;
    public String timeoutRole;

    //Auto responder module
    private AutoResponse[] autoResponses;

    //Moderation module
    public String[] moderatorRoles;
    public String logModerationChannel;
    public String logBanChannel;
    public String logKickChannel;

    //Modules
    public boolean isLoggingModule;
    public boolean isManagedMuteModule;
    public boolean isAutoResponderModule;
    public boolean isModerationModule;

}
