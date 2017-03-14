package com.mattsource.discordbucket.translation;

import com.mattsource.discordbucket.vocabulary.Parameters;

import javax.json.JsonObject;

interface TranslationProcessor {
    JsonObject translate(JsonObject jsonObject);

    Parameters parameter();
}
