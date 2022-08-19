package com.justinnelson.harmonymod.interactions.commands.slash;

import com.justinnelson.harmonymod.interactions.commands.CommandCategory;
import com.justinnelson.harmonymod.interactions.commands.commandprocessors.AbstractSlashCommander;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Modal;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;

public class SlashFeedback extends AbstractSlashCommander {

    public SlashFeedback() {
        super(CommandCategory.INFO);
    }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        logExecution();

        TextInput subject = TextInput.create("subject", "Subject", TextInputStyle.PARAGRAPH)
                .setPlaceholder("subject line")
                .setMinLength(4)
                .setMaxLength(30)
                .build();

        TextInput body = TextInput.create("body", "Body", TextInputStyle.PARAGRAPH)
                .setPlaceholder("Feedback")
                .setMinLength(10)
                .setMaxLength(300)
                .build();

        Modal modal = Modal.create("feedback", "Feedback")
                .addActionRows(ActionRow.of(subject), ActionRow.of(body))
                .build();
            event.replyModal(modal).queue();
    }
}

