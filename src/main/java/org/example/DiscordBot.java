package org.example;

import Commands.BotCommands;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;

import javax.security.auth.login.LoginException;

public class DiscordBot {
    public static void main(String[] args) throws LoginException, InterruptedException {
        //Creating the bot object. The string inside is the bot token
        JDA bot = JDABuilder.createDefault("enter token here")
                // This is for the activity status on Discord
                .setActivity(Activity.playing("blank"))
                // Activates the listeners for the slash commands
                .addEventListeners(new BotCommands())
                // Have to wait for the bot to be ready and fully activated otherwise commands don't work
                .build().awaitReady();

        // Put your guild ID in here for the bot to work. Bot has not been tuned for global operation yet.
        Guild guild = bot.getGuildById("635851828587921418");
        // If there is a valid guild ID, this will run the slash commands and parameters for slash commands.
        if (guild != null) {
            // /event command and its parameters
            guild.upsertCommand("event", "make an event")
                    .addOption(OptionType.STRING, "title", "the name or title of the event", true)
                    .addOption(OptionType.STRING, "date", "the date of the event. ex. 5/14/2024, August 14th 2024, Wednesday the 24th", true)
                    .addOption(OptionType.STRING, "description", "the description of the event", true)
                    .addOption(OptionType.STRING, "duration", "the duration of the event (preferably in hours or in a timeframe)", true)
                    .addOption(OptionType.STRING, "location", "the location of the event. If online, just say online or what channel/server", true)
                    .addOption(OptionType.USER, "attendees", "People you want to ping when creating this event", true)
                    .queue();

        }
    }
}
