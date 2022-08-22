package com.justinnelson.harmonymod.data.entities.helpers;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.internal.interactions.DeferrableInteractionImpl;
import net.dv8tion.jda.internal.interactions.InteractionHookImpl;

import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javax.annotation.Nonnull;

public class ModInteractionHook {

    InteractionHook hook;
    String target;
    String moderator;
    Instant start;
    Duration duration = Duration.ofMinutes(1);


    public ModInteractionHook(InteractionHook hook, String target, String moderator) {

        this.hook = hook;
        this.target = target;
        this.moderator = moderator;
        this.start = Instant.now();
        System.out.println(this.start);

    }

    public boolean isExpired() {
        boolean isExpired = false;
        Duration between = Duration.between(this.start, Instant.now());

        if (between.compareTo(this.duration) >= 0) {
            isExpired = true;
        }
        return isExpired;
    }
}
