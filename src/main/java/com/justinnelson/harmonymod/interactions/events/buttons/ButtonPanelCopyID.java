package com.justinnelson.harmonymod.interactions.events.buttons;

import com.justinnelson.harmonymod.core.utility.Util;
import com.justinnelson.harmonymod.interactions.events.eventprocessors.AbstractButtonHandler;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class ButtonPanelCopyID extends AbstractButtonHandler {

    @Override
    public void handle(ButtonInteractionEvent event) {

        logExecution();
        StringSelection stringSelection = new StringSelection(event.getButton().getId());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        Util.standardSuccess(event);

    }
}
