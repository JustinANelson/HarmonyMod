package com.justinnelson.harmonymod.utility;

import com.justinnelson.harmonymod.core.HarmonyMod;
import com.justinnelson.harmonymod.data.HMCollections;
import com.justinnelson.harmonymod.data.AppConfig;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;

import java.awt.Color;
import java.time.Duration;
import java.util.ArrayList;

public class Util {
    /*public static String getGuildPrefix(MessageReceivedEvent event){
        for(int x = 0; x < Variables.guildData.size(); x++) {
            if(Variables.guildData.get(x).getID() == event.getGuild().getIdLong()) {
                return Variables.guildData.get(x).getPrefix();
            }
        }
        return null;
    }*/
    public static void registerTestGuildParameters(){
        //HarmonyMod support server ID
        Guild guild = HarmonyMod.jda.getGuildById(AppConfig.SUPPORTSERVER);

        if (guild != null) {
            guild.updateCommands().addCommands(HMCollections.guildCommands).queue();
        }
    }
    public static void registerGlobalCommands(){
        HarmonyMod.jda.updateCommands().addCommands(new ArrayList<>()).queue();
    }
    public static String capitalize(String s) {
        if (s.length() == 0) {
            return s;
        }
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
    public static String formatDuration(Duration d) {
        long years = (d.toDays() / 365);
        d = d.minusDays(years * 365);
        long days = d.toDays();
        d = d.minusDays(days);
        long hours = d.toHours();
        d = d.minusHours(hours);
        long minutes = d.toMinutes();
        d = d.minusMinutes(minutes);
        long seconds = d.getSeconds();
        return
                (years == 0?"":years+" years, ")+
                        (days ==  0?"":days+" days, ")+
                        (hours == 0?"":hours+" hours, ")+
                        (minutes ==  0?"":minutes+" minutes, ")+
                        (seconds == 0?"":seconds+" seconds");
    }

    public static void standardSuccess(UserContextInteractionEvent event){
        event.replyEmbeds(basicMessageEmbed("Success. Thank you for using Harmony MOD.")).setEphemeral(true).queue();
    }
    public static void standardSuccess(SlashCommandInteractionEvent event){
        event.replyEmbeds(basicMessageEmbed("Success. Thank you for using Harmony MOD.")).setEphemeral(true).queue();
    }
    public static void standardSuccess(MessageContextInteractionEvent event){
        event.replyEmbeds(basicMessageEmbed("Success. Thank you for using Harmony MOD.")).setEphemeral(true).queue();
    }
    public static void standardSuccess(ButtonInteractionEvent event){
        event.replyEmbeds(basicMessageEmbed("Success. Thank you for using Harmony MOD.")).setEphemeral(true).queue();
    }
    public static void standardSuccess(ModalInteractionEvent event){
        event.replyEmbeds(basicMessageEmbed("Success. Thank you for using Harmony MOD.")).setEphemeral(true).queue();
    }
    public static void standardSuccess(SelectMenuInteractionEvent event) {
        event.replyEmbeds(basicMessageEmbed("Success. Thank you for using Harmony MOD.")).setEphemeral(true).queue();
    }

    public static MessageEmbed basicMessageEmbed(String str){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setDescription(str);
        embed.setColor(Color.CYAN);
        return embed.build();
    }

    public static MessageEmbed titledMessageEmbed(String author, String title, String str){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setAuthor(author);
        embed.setTitle(title);
        embed.setDescription(str);
        embed.setColor(Color.CYAN);
        return embed.build();
    }
    public static void logExecution(String actor, String action){

    }
    public static void logExecution(String actor, String target, String action) {

    }
}
