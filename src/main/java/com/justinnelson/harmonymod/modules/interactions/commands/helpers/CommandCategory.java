package com.justinnelson.harmonymod.modules.interactions.commands.helpers;

public enum CommandCategory {
    ADMIN(CommandPermission.ADMIN, "categories.admin", "Admin"),
    MODERATION(CommandPermission.MODERATOR, "categories.moderation", "Moderation"),
    OWNER(CommandPermission.OWNER, "categories.owner", "Owner"),
    INFO(CommandPermission.USER, "categories.info", "Info"),
    UTILS(CommandPermission.USER, "categories.utils", "Utility");

    public final CommandPermission permission;
    private final String s;
    private final String qualifiedName;

    CommandCategory(CommandPermission p, String s, String name) {
        this.permission = p;
        this.s = s;
        this.qualifiedName = name;
    }
    @Override
    public String toString() {
        return s;
    }
}