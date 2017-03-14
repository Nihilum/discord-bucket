package com.mattsource.discordbucket.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum ConfigService {
    INSTANCE;

    private static final String PROPERTIES_FILENAME = "discordbucket.properties";
    private final Config config;

    ConfigService() {
        boolean succeeded = true;

        Properties properties = new Properties();

        int port = 0;
        String webHook = null;
        String userAgent = null;

        try {
            try (InputStream inputStream = new FileInputStream(PROPERTIES_FILENAME)) {
                properties.load(inputStream);
            }

            port = Integer.valueOf(properties.getProperty(
                    com.mattsource.discordbucket.vocabulary.Properties.PORT.getProperty()));
            webHook = properties.getProperty(
                    com.mattsource.discordbucket.vocabulary.Properties.WEBHOOK.getProperty());
            userAgent = properties.getProperty(
                    com.mattsource.discordbucket.vocabulary.Properties.USERAGENT.getProperty());
        } catch (FileNotFoundException e) {
            System.out.println("Can not find " + PROPERTIES_FILENAME + " file.");
            succeeded = false;
        } catch (IOException e) {
            System.out.println("Error while processing " + PROPERTIES_FILENAME + " file.");
            succeeded = false;
        } catch (NumberFormatException e) {
            System.out.println("Error while reading one of properties.");
            succeeded = false;
        }

        if (succeeded) {
            config = new ConfigImpl(port, webHook, userAgent);
        } else {
            config = null;
        }
    }

    public Config getConfig() {
        return config;
    }
}
