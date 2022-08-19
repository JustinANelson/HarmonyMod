package com.justinnelson.harmonymod.commands.slash;

import com.justinnelson.harmonymod.commands.CommandCategory;
import com.justinnelson.harmonymod.commands.commandprocessors.AbstractSlashCommander;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class SlashTime extends AbstractSlashCommander {

    public SlashTime() {
        super(CommandCategory.UTILS);
    }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        logExecution();
        long creationTime = System.currentTimeMillis();
        boolean ephemeral = true;
        OptionMapping option = event.getOptions().stream().filter(e -> e.getName().equals("ephemeral")).findAny().orElse(null);
        if (option != null) {
            ephemeral = option.getAsBoolean();
        }


        EmbedBuilder time = new EmbedBuilder();
        time.setColor(0x66d8ff);
        time.setTitle("Times across the world");
        time.setImage("https://image.freepik.com/free-vector/polygonal-map-digital-globe-map-blue-polygons-earth-maps-world-internet-connection-3d-grid-illustration_102902-902.jpg");
        time.addField("New York:", tzf("America/New_York"), true);
        time.addField("Chicago:", tzf("America/Chicago"), true);
        time.addField("Denver:", tzf("America/Denver"), true);
        time.addField("Phoenix:", tzf("America/Phoenix"), true);
        time.addField("Los Angeles:", tzf("America/Los_Angeles"), true);
        time.addField("Anchorage:", tzf("America/Anchorage"), true);
        time.addField("Honolulu:", tzf("Pacific/Honolulu"), true);
        time.addField("London:", tzf("Europe/London"), true);
        time.addField("Stockholm:", tzf("Europe/Stockholm"), true);
        time.addField("Perth:", tzf("Australia/Perth"), true);
        time.addField("Adelaide:", tzf("Australia/Adelaide"), true);
        time.addField("Sydney:", tzf("Australia/Sydney"), true);
        time.addField("South Korea:", tzf("Asia/Seoul"), true);
        time.addField("Berlin:", tzf("Europe/Berlin"), true);
        time.addField("Hong Kong:", tzf("Asia/Hong_Kong"), true);

        event.replyEmbeds(time.build()).setEphemeral(ephemeral)
                .flatMap(v ->
                        event.getHook().editOriginalFormat("Time: %d ms", System.currentTimeMillis() - creationTime) // then edit original
                ).queue();
    }

    public String tzf(String timeZone) {
        Date today = new Date();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy - HH:mm:ss z");
        df.setTimeZone(TimeZone.getTimeZone(timeZone));

        return df.format(today);
    };
}
