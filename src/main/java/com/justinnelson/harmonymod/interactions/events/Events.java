package com.justinnelson.harmonymod.interactions.events;

import com.justinnelson.harmonymod.interactions.commands.events.modals.ModalFeedback;
import com.justinnelson.harmonymod.interactions.commands.events.moderations.ModerationBan;
import com.justinnelson.harmonymod.interactions.commands.events.moderations.ModerationKick;
import com.justinnelson.harmonymod.interactions.commands.events.moderations.ModerationLookup;
import com.justinnelson.harmonymod.interactions.commands.events.moderations.ModerationMute;
import com.justinnelson.harmonymod.interactions.events.buttons.ButtonMute;
import com.justinnelson.harmonymod.interactions.events.buttons.ButtonToggleRole;
import com.justinnelson.harmonymod.interactions.events.eventprocessors.ButtonHandler;
import com.justinnelson.harmonymod.interactions.events.eventprocessors.ModalHandler;
import com.justinnelson.harmonymod.interactions.events.eventprocessors.ModerationHandler;

import java.util.ArrayList;

public class Events {
    public static ArrayList<ButtonHandler> buttonEvents = new ArrayList<>();
    public static ArrayList<ModalHandler> modalEvents = new ArrayList<>();
    public static ArrayList<ModerationHandler> moderationEvents = new ArrayList<>();

    public static void register() {

        //Buttons
        new ButtonMute();
        new ButtonToggleRole();

        //Modals
        new ModalFeedback();

        //Moderations
        new ModerationMute();
        new ModerationKick();
        new ModerationLookup();
        new ModerationBan();

    }

    public static void registerButtons(ButtonHandler buttonHandler) {
        buttonEvents.add(buttonHandler);
    }
    public static void registerModals(ModalHandler modalHandler) {
        modalEvents.add(modalHandler);
    }
    public static void registerModeration(ModerationHandler moderationHandler) {
        moderationEvents.add(moderationHandler);
    }
}
