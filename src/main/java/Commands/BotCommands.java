package Commands;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class BotCommands extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        if (event.getName().equals("event")){
            // to make it so only the user can see the reply, use .setEphemeral(true) before queuing
            // Create option objects
            OptionMapping title = event.getOption("title");
            OptionMapping date = event.getOption("date");
            OptionMapping description = event.getOption("description");
            OptionMapping duration = event.getOption("duration");
            OptionMapping location = event.getOption("location");
            OptionMapping attendees = event.getOption("attendees");
            // Just in case the title isn't inputted, avoiding nullpointerexception (Should add for each of the options, but too lazy rn)
            if (title == null){
                event.reply("you didn't give a title");
                return;
            }
            // Variables used for options
            String eventTitle = title.getAsString();
            String eventDate = date.getAsString();
            String eventDescription = description.getAsString();
            String eventDuration = duration.getAsString();
            String eventLocation = location.getAsString();
            // eventAttendees is different because it needs to ping people instead of just pulling the userID
            String eventAttendees;
            // Made attendeeUser object to fetch the userID to ping
            User attendeeUser = attendees.getAsUser();
            eventAttendees = attendeeUser.getAsMention();
            // Main reply box, prints out the text for the event (will turn into rich embed in the future, just trying to get slash commands working)
            event.reply(":calendar_spiral: **" + eventTitle + "** " +
                    "\n" + eventDescription + "\n\n:clock1: **Time** " +
                    "\n" + eventDate + "\n\n:rotating_light: **Duration**" +
                    "\n" + eventDuration + "\n\n:map: **Location**" +
                    "\n" + eventLocation + "\n\n:runner: **Attendees**" +
                    "\n" + eventAttendees).queue();
        // to make more commands, just put it all into an else if after the first if statement
        }
    }
}
