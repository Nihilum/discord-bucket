package com.mattsource.discordbucket.translation;

import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.mattsource.discordbucket.vocabulary.Parameters;
import org.reflections.Reflections;

import javax.json.JsonObject;

enum TranslationService {
    INSTANCE;

    private final Map<Parameters, Class<?>> translators;

    TranslationService() {
        this.translators = initializeTranslators();
    }

    @SuppressWarnings("unchecked")
    public TranslationProcessor createTranslator(JsonObject jsonObject) throws Exception {
        for (Parameters parameter : Parameters.values()) {
            if (jsonObject.containsKey(parameter.getValue())) {

                Class<?> clazz = translators.get(parameter);

                if (clazz != null) {
                    return (TranslationProcessor) clazz.getDeclaredConstructor().newInstance();
                }
            }
        }

        throw new IllegalArgumentException("Given jsonObject does not have any recognizable parameter.");
    }

    private Map<Parameters, Class<?>> initializeTranslators() {
        Map<Parameters, Class<?>> translators = new HashMap<>();

        Reflections reflections = new Reflections("com.mattsource.discordbucket.translation");

        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Translator.class);

        for (Class<?> clazz : classes) {
            Translator translator = clazz.getAnnotation(Translator.class);

            if (translator != null) {
                translators.put(translator.value(), clazz);
            }
        }

        return translators;
    }
}
