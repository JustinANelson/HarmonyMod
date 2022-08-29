package com.justinnelson.harmonymod.data.entities.helpers;

import java.util.ArrayList;

public class MutedMember {

    public String guildID;
    public String memberID;
    public ArrayList<String> removedRoles = new ArrayList<>();

    public String getGuildID() {return guildID;}
    public String getMemberID() {return memberID;}
    public ArrayList<String> getRemovedRoles() {return removedRoles;}

    public void setGuildID(String guildID) {this.guildID = guildID;}
    public void setMemberID(String memberID) {this.memberID = memberID;}
    public void setRemovedRoles(ArrayList<String> removedRoles) {this.removedRoles = removedRoles;}
}
