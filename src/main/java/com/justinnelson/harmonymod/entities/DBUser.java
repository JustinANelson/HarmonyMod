package com.justinnelson.harmonymod.entities;

import com.justinnelson.harmonymod.entities.helpers.UserData;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;

public class DBUser {
    private final String id;
    private final UserData data;

    public DBUser(String id, UserData data) {
        this.id = id;
        this.data = data;
    }
    public User getUser(JDA jda) {
        return jda.retrieveUserById(getId()).complete();
    }
    public String getId() {
        return this.id;
    }
    public static DBUser of(String id) {
        return new DBUser(id, new UserData());
    }
}
