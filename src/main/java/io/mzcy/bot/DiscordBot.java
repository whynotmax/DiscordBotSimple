package io.mzcy.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.apache.commons.collections4.list.UnmodifiableList;

import java.util.EnumSet;
import java.util.List;

public class DiscordBot {

    String token;
    EnumSet<GatewayIntent> intents;

    JDA jda;

    private DiscordBot(String token, EnumSet<GatewayIntent> intents) {
        this.token = token;
        this.intents = intents;

        JDABuilder jdaBuilder = JDABuilder.createDefault(token, UnmodifiableList.unmodifiableList(intents.stream().toList()));
        jdaBuilder.setStatus(OnlineStatus.ONLINE);
        jdaBuilder.setActivity(Activity.playing("mit Leonard's Lümmel"));

        try {
            this.jda = jdaBuilder.build().awaitStatus(JDA.Status.CONNECTED);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String token;
        private EnumSet<GatewayIntent> intents;

        public Builder setToken(String token) {
            this.token = token;
            return this;
        }

        public Builder setIntents(EnumSet<GatewayIntent> intents) {
            this.intents = intents;
            return this;
        }

        public DiscordBot build() {
            return new DiscordBot(token, intents);
        }
    }

}
