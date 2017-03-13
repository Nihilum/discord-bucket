package com.mattsource.discordbucket.translation;

import com.mattsource.discordbucket.logging.Logger;
import com.mattsource.discordbucket.logging.LoggerFactory;
import com.mattsource.discordbucket.vocabulary.Objects;
import com.mattsource.discordbucket.vocabulary.Parameters;

import javax.json.*;
import java.util.Map;

@Translator(Parameters.PUSH)
public class PushTranslationProcessor implements TranslationProcessor {
    private static final Logger LOG = LoggerFactory.INSTANCE
            .getLogger(PushTranslationProcessor.class);

    @Override
    public JsonObject translate(JsonObject jsonObject) {
        JsonObject actor = jsonObject.getJsonObject(Objects.ACTOR.getValue());

        if (actor == null) {
            throw new JsonException("JSON is missing '" + Objects.ACTOR.getValue() + "'.");
        }

        JsonValue username = actor.get(Objects.USERNAME.getValue());

        if (username == null) {
            throw new JsonException("JSON is missing '" + Objects.USERNAME.getValue() + "'.");
        }

        JsonObject push = jsonObject.getJsonObject(Objects.PUSH.getValue());

        if (push == null) {
            throw new JsonException("JSON is missing '" + Objects.PUSH.getValue() + "'.");
        }

        JsonObject repository = jsonObject.getJsonObject(Objects.REPOSITORY.getValue());

        if (repository == null) {
            throw new JsonException("JSON is missing '" + Objects.REPOSITORY.getValue() + "'.");
        }

        JsonArray changes = push.getJsonArray(Objects.CHANGES.getValue());

        if (changes == null) {
            throw new JsonException("JSON is missing '" + Objects.CHANGES.getValue() + "'.");
        }

        StringBuilder messageBuilder = new StringBuilder();

        for (int i = 0; i < changes.size(); ++i) {
            JsonObject change = changes.getJsonObject(i);

            if (change == null) {
                throw new JsonException("Empty JSON object in " + Objects.CHANGES.getValue() + " array.");
            }

            JsonArray commits = change.getJsonArray(Objects.COMMITS.getValue());

            if (commits == null) {
                throw new JsonException("JSON is missing '" + Objects.COMMITS.getValue() + "'.");
            }

            for (int j = 0; j < commits.size(); ++j) {
                JsonObject commit = commits.getJsonObject(j);

                if (commit == null) {
                    throw new JsonException("Empty JSON object in " + Objects.COMMITS.getValue() + " array.");
                }

                JsonObject links = commit.getJsonObject(Objects.LINKS.getValue());

                if (links == null) {
                    throw new JsonException("JSON is missing '" + Objects.LINKS.getValue() + "'.");
                }

                JsonObject html = links.getJsonObject(Objects.HTML.getValue());

                if (html == null) {
                    throw new JsonException("JSON is missing '" + Objects.HTML.getValue() + "'.");
                }

                JsonValue href = html.get(Objects.HREF.getValue());

                if (href == null) {
                    throw new JsonException("JSON is missing '" + Objects.HREF.getValue() + "'.");
                }

                JsonValue message = commit.get(Objects.MESSAGE.getValue());

                if (message == null) {
                    throw new JsonException("JSON is missing '" + Objects.MESSAGE.getValue() + "'.");
                }

                messageBuilder.append("User ").append(username.toString()).append(" has made a following commit: ");
                messageBuilder.append("\n\n");
                messageBuilder.append(message.toString().replace("\"", ""));
                messageBuilder.append("\n\n");
                messageBuilder.append(href.toString());
                messageBuilder.append("\n\n");
            }
        }

        JsonObject discordJson = Json.createObjectBuilder()
                .add(Objects.USERNAME.getValue(), "Discord Bucket")
                .add(Objects.CONTENT.getValue(), messageBuilder.toString().replace("\\n", "\n"))
                .build();

        for (Map.Entry<String, JsonValue> s : jsonObject.entrySet()) {
            LOG.debug(s.getKey() + "====" + s.getValue());
        }

        return discordJson;
    }

    @Override
    public Parameters parameter() {
        return Parameters.PUSH;
    }
}
