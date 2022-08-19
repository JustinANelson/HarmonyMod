package com.justinnelson.harmonymod.commands.slash;


import com.justinnelson.harmonymod.commands.commandprocessors.AbstractSlashCommander;
import com.justinnelson.harmonymod.commands.CommandCategory;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class SlashPing extends AbstractSlashCommander {

    public SlashPing(){
        super(CommandCategory.UTILS);
    }

    @Override
    public void handle(SlashCommandInteractionEvent event){
        long time = System.currentTimeMillis();
        event.reply("Pong!").setEphemeral(true) // reply or acknowledge
                .flatMap(v ->
                        event.getHook().editOriginalFormat("Pong: %d ms", System.currentTimeMillis() - time) // then edit original
                ).queue(); // Queue both reply and edit
    }
}
