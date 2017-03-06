package com.mattsource.discordbucket.translation;

import com.mattsource.discordbucket.logging.Logger;
import com.mattsource.discordbucket.logging.LoggerFactory;
import com.mattsource.discordbucket.scheduler.SchedulerService;
import com.mattsource.discordbucket.scheduler.SchedulerTask;
import com.mattsource.discordbucket.scheduler.TaskService;
import com.mattsource.discordbucket.vocabulary.Types;

import javax.json.JsonObject;
import java.util.concurrent.Callable;

@SchedulerTask(Types.TRANSLATION)
public class TranslationTask implements Callable<Void> {
    private static final Logger LOG = LoggerFactory.INSTANCE.getLogger(TranslationTask.class);

    private final JsonObject jsonObject;

    public TranslationTask(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    @Override
    public Void call() throws Exception {
        if (jsonObject == null) {
            return null;
        }

        TranslationProcessor processor = TranslationService.INSTANCE.createTranslator(jsonObject);

        LOG.info("Translating '{}' event.", processor.parameter().getValue());

        JsonObject translatedJson = processor.translate(jsonObject);

        SchedulerService.INSTANCE
                .schedule(TaskService.INSTANCE.createTask(Types.REST, translatedJson));

        return null;
    }
}
