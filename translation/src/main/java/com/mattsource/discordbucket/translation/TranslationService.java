package com.mattsource.discordbucket.translation;

import javax.json.JsonObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public enum TranslationService {
    INSTANCE;

    private final ExecutorService executor;

    TranslationService() {
        this.executor = Executors.newWorkStealingPool();
    }

    public void translate(JsonObject jsonObject) {
        executor.submit(new TranslationSession(jsonObject));
    }
}
