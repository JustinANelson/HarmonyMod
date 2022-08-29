package com.justinnelson.harmonymod.data.entities.helpers;

import java.util.ArrayList;

public class MutedMember {

    public String guildID;
    public ArrayList<String> removedRoles = new ArrayList<>();

    public String getGuildID() {return guildID;}
    public ArrayList<String> getRemovedRoles() {return removedRoles;}

    public void setGuildID(String guildID) {this.guildID = guildID;}
    public void setRemovedRoles(ArrayList<String> removedRoles) {this.removedRoles = removedRoles;}
}
