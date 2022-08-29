package com.justinnelson.harmonymod.data.entities;

import com.justinnelson.harmonymod.data.entities.helpers.MutedMember;

import java.util.ArrayList;

/*
    Used to store nickname changes, mutes, username changes.
 */
public class UserEntity {
    private String id;
    private String timezone;
    private ArrayList<String> nicknames = new ArrayList<>();
    private ArrayList<String> usernames = new ArrayList<>();
    private ArrayList<MutedMember> mutedMemberEntities = new ArrayList<>();

    public void setId(String id) {this.id = id;}
    public void setTimezone(String timezone) {this.timezone = timezone;}
    public void setNicknames(ArrayList<String> nicknames) {this.nicknames = nicknames;}
    public void setUsernames(ArrayList<String> usernames) {this.usernames = usernames;}
    public void setMutedMemberEntities(ArrayList<MutedMember> mutedMemberEntities) {this.mutedMemberEntities = mutedMemberEntities;}

    public String getId() {return id;}
    public String getTimezone() {return timezone;}
    public ArrayList<String> getNicknames() {return nicknames;}
    public ArrayList<String> getUsernames() {return usernames;}
    public ArrayList<MutedMember> getMutedMemberEntities() {return mutedMemberEntities;}

    public void addMutedMember(MutedMember mutedMember) {
        mutedMemberEntities.add(mutedMember);
    }
}
