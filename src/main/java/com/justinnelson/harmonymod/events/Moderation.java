package com.justinnelson.harmonymod.events;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.InteractionHook;

public class Moderation {
    EventType eventType = EventType.Moderation;
    String name;
    Member modPanelMember;
    boolean muteFlag;
    InteractionHook hook;

    public InteractionHook getHook() {
        return hook;
    }

    public void setHook(InteractionHook hook) {
        this.hook = hook;
    }
    public boolean isMuteFlag() {
        return muteFlag;
    }
    public void setMuteFlag(boolean muteFlag) {
        this.muteFlag = muteFlag;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Member getModPanelMember() {
        return modPanelMember;
    }
    public void setModPanelMember(Member modPanelMember) {
        this.modPanelMember = modPanelMember;
    }
}
