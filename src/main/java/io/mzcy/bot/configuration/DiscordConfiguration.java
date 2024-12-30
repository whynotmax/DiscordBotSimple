package io.mzcy.bot.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class DiscordConfiguration {

    String token;
    long guildId;
    long ownerId;

    public DiscordConfiguration() {
        File folder = new File("config");
        if (!folder.exists()) {
            folder.mkdir();
        }
        File file = new File("config/discord.properties");
        if (!file.exists()) {
            try {
                file.createNewFile();
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("token=TOKEN_HERE\n");
                    writer.write("guildId=GUILD_ID_HERE\n");
                    writer.write("ownerId=OWNER_ID_HERE\n");
                    writer.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            InputStream fileInputStream = file.toURI().toURL().openStream();
            Properties properties = new Properties();
            properties.load(fileInputStream);

            token = properties.getProperty("token");
            guildId = Long.parseLong(properties.getProperty("guildId"));
            ownerId = Long.parseLong(properties.getProperty("ownerId"));

        } catch (IOException e) {
            throw new IllegalStateException("Could not load discord configuration.", e);
        }
    }

}
