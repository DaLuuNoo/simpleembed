package org.example;

import embeds.EmbedCreator;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {

    public static void main(String[] args) {

        String token = "YOUR TOKEN HERE";
        String status = "YOUR STATUS HERE";

        JDABuilder jdaBuilder = JDABuilder.createDefault(token)
                .setActivity(Activity.playing(status))
                .addEventListeners(new EmbedCreator())
                .enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_PRESENCES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.DIRECT_MESSAGES);

        var jda = jdaBuilder.build();


        System.out.println("Simple Embed System is working!");
    }
}