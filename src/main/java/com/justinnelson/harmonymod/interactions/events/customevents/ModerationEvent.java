package com.justinnelson.harmonymod.interactions.events.customevents;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.InteractionHook;

public class ModerationEvent {
    String name;
    Member modPanelMember;
    boolean muteFlag;
    InteractionHook hook;

    public void setHook(InteractionHook hook) {
        this.hook = hook;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setModPanelMember(Member modPanelMember) {
        this.modPanelMember = modPanelMember;
    }
    public void setMuteFlag(boolean muteFlag) {
        this.muteFlag = muteFlag;
    }

    public InteractionHook getHook() {
        return hook;
    }
    public String getName() {
        return name;
    }
    public Member getModPanelMember() {
        return modPanelMember;
    }
    public boolean isMuteFlag() {
        return muteFlag;
    }

}
