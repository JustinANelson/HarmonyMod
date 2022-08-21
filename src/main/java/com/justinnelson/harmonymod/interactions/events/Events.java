package com.justinnelson.harmonymod.interactions.events;

import com.justinnelson.harmonymod.interactions.events.buttons.ButtonPanelCopyID;
import com.justinnelson.harmonymod.interactions.events.eventprocessors.ButtonPanelHandler;
import com.justinnelson.harmonymod.interactions.events.modals.ModalFeedback;
import com.justinnelson.harmonymod.interactions.events.moderations.ModerationBan;
import com.justinnelson.harmonymod.interactions.events.moderations.ModerationKick;
import com.justinnelson.harmonymod.interactions.events.moderations.ModerationLookup;
import com.justinnelson.harmonymod.interactions.events.moderations.ModerationMute;
import com.justinnelson.harmonymod.interactions.events.buttons.ButtonMute;
import com.justinnelson.harmonymod.interactions.events.buttons.ButtonToggleRole;
import com.justinnelson.harmonymod.interactions.events.eventprocessors.ButtonHandler;
import com.justinnelson.harmonymod.interactions.events.eventprocessors.ModalHandler;
import com.justinnelson.harmonymod.interactions.events.eventprocessors.ModerationHandler;
import com.justinnelson.harmonymod.interactions.events.moderations.ModerationTimeout;

import java.util.ArrayList;

public class Events {
    public static ArrayList<ButtonHandler> buttonEvents = new ArrayList<>();
    public static ArrayList<ButtonPanelHandler> buttonPanelEvents = new ArrayList<>();
    public static ArrayList<ModalHandler> modalEvents = new ArrayList<>();
    public static ArrayList<ModerationHandler> moderationEvents = new ArrayList<>();

    public static void register() {

        //Buttons
        new ButtonMute();
        new ButtonToggleRole();

        //ModPanel
        new ButtonPanelCopyID();

        //Modals
        new ModalFeedback();

        //Moderations
        new ModerationMute();
        new ModerationKick();
        new ModerationLookup();
        new ModerationBan();
        new ModerationTimeout();

    }

    public static void registerButtons(ButtonHandler buttonHandler) {
        buttonEvents.add(buttonHandler);
    }
    public static void registerButtons(ButtonPanelHandler buttonPanelHandler) {
        buttonPanelEvents.add(buttonPanelHandler);
    }
    public static void registerModals(ModalHandler modalHandler) {
        modalEvents.add(modalHandler);
    }
    public static void registerModeration(ModerationHandler moderationHandler) {
        moderationEvents.add(moderationHandler);
    }
}
