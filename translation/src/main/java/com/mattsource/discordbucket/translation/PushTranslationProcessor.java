package com.mattsource.discordbucket.translation;

import com.mattsource.discordbucket.logging.Logger;
import com.mattsource.discordbucket.logging.LoggerFactory;
import com.mattsource.discordbucket.vocabulary.Parameters;

import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.Map;

@Translator(Parameters.PUSH)
public class PushTranslationProcessor implements TranslationProcessor {
    private static final Logger LOG = LoggerFactory.INSTANCE.getLogger(PushTranslationProcessor.class);

    @Override
    public JsonObject translate(JsonObject jsonObject) {
        for (Map.Entry<String, JsonValue> s : jsonObject.entrySet()) {
            LOG.info(s.getKey() + "====" + s.getValue());
        }

        return jsonObject;
    }

    @Override
    public Parameters parameter() {
        return Parameters.PUSH;
    }
}
