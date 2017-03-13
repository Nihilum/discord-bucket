package com.mattsource.discordbucket.rest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

import javax.json.JsonObject;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import com.mattsource.discordbucket.logging.Logger;
import com.mattsource.discordbucket.logging.LoggerFactory;
import com.mattsource.discordbucket.scheduler.SchedulerTask;
import com.mattsource.discordbucket.vocabulary.Types;

@SchedulerTask(Types.REST)
public class RestTask implements Callable<Void> {
    private static final Logger LOG = LoggerFactory.INSTANCE.getLogger(RestTask.class);

    private final JsonObject jsonObject;
    private final String webHook;

    public RestTask(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
        this.webHook = "https://discordapp.com/api/webhooks/286168571539881984/3ih6v_3cz4DL2E6NQj1FyrZrWTV2mhsrGtfl-2aO1fxQyAh7baA4Fr4mA6Y0xL-kbiuj";
    }

    @Override
    public Void call() throws Exception {
        LOG.info("Running REST task.");

        if (jsonObject == null) {
            LOG.error("There is no JSON to be sent.");
            return null;
        }

        String json = jsonObject.toString();

        try {
            URL url = new URL(webHook);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if (connection == null) {
                LOG.error("Can not establish connection to Discord.");
                return null;
            }

            LOG.info("Established new connection with {}.", connection.getURL().toString());

            try {
                connection.setRequestMethod(HttpMethod.POST);
                connection.setRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
                connection.setRequestProperty(HttpHeaders.CONTENT_LENGTH,
                        String.valueOf(json.length()));
                connection.setRequestProperty(HttpHeaders.USER_AGENT,
                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; FSL 7.0.6.01001)");

                connection.setUseCaches(false);
                connection.setDoInput(true);
                connection.setDoOutput(true);

                try (DataOutputStream outputStream = new DataOutputStream(
                        connection.getOutputStream())) {

                    LOG.debug("Sending: {}", json);
                    outputStream.writeBytes(json);
                    outputStream.flush();
                }

                try (DataInputStream input = new DataInputStream(connection.getInputStream())) {
                    StringBuilder stringBuilder = new StringBuilder();

                    for (int c = input.read(); c != -1; c = input.read()) {
                        stringBuilder.append((char) c);
                    }

                    LOG.debug("Received: '{}'.", stringBuilder.toString());
                }
            } finally {
                connection.disconnect();
                LOG.info("Disconnected from discord.");
            }

        } catch (Exception e) {
            LOG.error(e.getMessage());
        }

        return null;
    }
}
