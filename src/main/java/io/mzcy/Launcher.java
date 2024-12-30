package io.mzcy;

import io.mzcy.bot.DiscordBot;
import io.mzcy.bot.configuration.DiscordConfiguration;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Launcher {

    public static void main(String[] args) {
        DiscordConfiguration configuration = new DiscordConfiguration();
        DiscordBot.builder().setToken(configuration.getToken()).setIntents(GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS)).build();
    }

}
