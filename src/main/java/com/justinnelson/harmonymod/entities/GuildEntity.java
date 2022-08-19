package com.justinnelson.harmonymod.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/*
None of this accounts for individual guilds yet.
Needs to be updated prior to use.
 */
public class GuildEntity {
    String ID;
    String name;
    String prefix;
    List<String> modRoles = new ArrayList<>();
    List<String> aliases = new ArrayList<>();

    public GuildEntity(String ID, String name) {
        new GuildEntity(ID, name, "=");
    }
    public GuildEntity(String ID, String name, String prefix) {
        this.ID = ID;
        this.name = name;
        this.prefix = prefix;
    }

    public String getID() {
        return this.ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getName() {
        return this.name;
    }
    public List<String> getModRoles() {return modRoles;}
    public List<String> getAliases() {return aliases;}

    public void setName(String name) {
        this.name = name;
    }
    public String getPrefix() {
        return prefix;
    }
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
    public void setModRoles(List<String> modRoles) {
        this.modRoles = modRoles;
    }
    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }
    public void addModRole(String role){
        modRoles.add(role);
    }
    public void removeModRole(String role){
        modRoles.remove(role);
    }
    public void addAlias(String alias){
        aliases.add(alias);
    }
    public void removeAlias(String alias){
        aliases.remove(alias);
    }
}
