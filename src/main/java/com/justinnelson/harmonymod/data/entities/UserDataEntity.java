package com.justinnelson.harmonymod.data.entities;

import net.dv8tion.jda.api.entities.User;

import java.time.format.DateTimeFormatter;

public class UserDataEntity {
    private String id;
    private String name;
    private String timeCreated;

    public UserDataEntity(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.timeCreated = user.getTimeCreated().format(DateTimeFormatter.RFC_1123_DATE_TIME);
    }

    public void setId(String id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setTimeCreated(String timeCreated) {this.timeCreated = timeCreated;}

    public String getId() {return id;}
    public String getName() {return name;}
    public String getTimeCreated() {return timeCreated;}
}
