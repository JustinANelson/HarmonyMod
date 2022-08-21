package com.justinnelson.harmonymod.data.entities;

import net.dv8tion.jda.api.entities.User;

import java.time.format.DateTimeFormatter;

/*
    Used to store nickname changes.
 */
public class UserDataEntity {
    private String id;
    private String[] nicknames;

    public UserDataEntity(User user) {
        this.id = user.getId();
    }

    public void setId(String id) {this.id = id;}
    public void setNicknames(String[] nicknames) {this.nicknames = nicknames;}


    public String getId() {return id;}
    public String[] getNicknames() {return nicknames;}
}
