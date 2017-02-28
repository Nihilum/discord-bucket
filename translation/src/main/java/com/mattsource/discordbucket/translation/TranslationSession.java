package com.mattsource.discordbucket.translation;

import com.mattsource.discordbucket.logging.Logger;
import com.mattsource.discordbucket.logging.LoggerFactory;

import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.Map;

class TranslationSession implements Runnable {
    private static final Logger LOG = LoggerFactory.INSTANCE.getLogger(TranslationSession.class);

    private final JsonObject jsonObject;

    TranslationSession(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    @Override
    public void run() {
        for (Map.Entry<String, JsonValue> s : jsonObject.entrySet()) {
            LOG.info(s.getKey() + "====" + s.getValue());
        }
    }
}
