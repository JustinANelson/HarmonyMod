package com.justinnelson.harmonymod.interactions.events;

import com.justinnelson.harmonymod.interactions.events.buttons.ButtonFixPermissions;
import com.justinnelson.harmonymod.interactions.events.buttons.ButtonPanelCopyID;
import com.justinnelson.harmonymod.interactions.events.buttons.ButtonPanelLookup;
import com.justinnelson.harmonymod.interactions.events.buttons.ButtonPanelMute;
import com.justinnelson.harmonymod.interactions.events.buttons.ButtonPanelNickname;
import com.justinnelson.harmonymod.interactions.events.buttons.ButtonPanelTimeout;
import com.justinnelson.harmonymod.interactions.events.buttons.ButtonResetPermissions;
import com.justinnelson.harmonymod.interactions.events.eventprocessors.SelectMenuHandler;
import com.justinnelson.harmonymod.interactions.events.modals.ModalFeedback;
import com.justinnelson.harmonymod.interactions.events.moderations.ModerationBan;
import com.justinnelson.harmonymod.interactions.events.moderations.ModerationKick;
import com.justinnelson.harmonymod.interactions.events.moderations.ModerationLookup;
import com.justinnelson.harmonymod.interactions.events.moderations.ModerationMute;
import com.justinnelson.harmonymod.interactions.events.buttons.ButtonToggleRole;
import com.justinnelson.harmonymod.interactions.events.eventprocessors.ButtonHandler;
import com.justinnelson.harmonymod.interactions.events.eventprocessors.ModalHandler;
import com.justinnelson.harmonymod.interactions.events.eventprocessors.ModerationHandler;
import com.justinnelson.harmonymod.interactions.events.moderations.ModerationTimeout;
import com.justinnelson.harmonymod.interactions.events.selectionmenus.SelectMenuFixPermissions;
import com.justinnelson.harmonymod.interactions.events.selectionmenus.SelectMenuResetPermissions;

import java.util.ArrayList;

public class Events {
    public static ArrayList<ButtonHandler> buttonEvents = new ArrayList<>();
    public static ArrayList<ModalHandler> modalEvents = new ArrayList<>();
    public static ArrayList<ModerationHandler> moderationEvents = new ArrayList<>();
    public static ArrayList<SelectMenuHandler> selectMenuEvents = new ArrayList<>();

    public static void register() {

        //Buttons
        new ButtonToggleRole();

        //ModPanel
        new ButtonPanelCopyID();
        new ButtonPanelLookup();
        new ButtonPanelMute();
        new ButtonPanelNickname();
        new ButtonPanelTimeout();
        new ButtonFixPermissions();
        new ButtonResetPermissions();

        //Modals
        new ModalFeedback();

        //Moderations
        new ModerationMute();
        new ModerationKick();
        new ModerationLookup();
        new ModerationBan();
        new ModerationTimeout();

        //SelectMenu
        new SelectMenuFixPermissions();
        new SelectMenuResetPermissions();

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
    public static void registerSelectMenus(SelectMenuHandler selectMenuHandler) {
        selectMenuEvents.add(selectMenuHandler);
    }
}
