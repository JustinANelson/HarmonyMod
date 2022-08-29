package com.justinnelson.harmonymod.data.db.dto;

import com.justinnelson.harmonymod.data.entities.helpers.MutedMember;

import java.util.ArrayList;

public class UserDTO {

    public String id;
    public String timezone;
    public ArrayList<String> nicknames = new ArrayList<>();
    public ArrayList<String> usernames = new ArrayList<>();
    public ArrayList<MutedMember> mutedMember = new ArrayList<>();

}
