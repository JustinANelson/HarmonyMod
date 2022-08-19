package com.justinnelson.harmonymod.events;

import com.justinnelson.harmonymod.data.HBCollections;

public class RoleButton {
    EventType eventType = EventType.RoleButton;
    String id;
    String role;

    public RoleButton(String eventIDString, String label) {
        this.id = eventIDString;
        this.role = label;
        HBCollections.roleButtons.add(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
