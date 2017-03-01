package com.mattsource.discordbucket.translation;

import com.mattsource.discordbucket.logging.Logger;
import com.mattsource.discordbucket.logging.LoggerFactory;
import com.mattsource.discordbucket.scheduler.SchedulerService;
import com.mattsource.discordbucket.scheduler.SchedulerTask;
import com.mattsource.discordbucket.scheduler.TaskService;
import com.mattsource.discordbucket.vocabulary.Types;

import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.Map;
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
        LOG.info("Running TRANSLATION task.");

        if (jsonObject == null) {
            return null;
        }

        for (Map.Entry<String, JsonValue> s : jsonObject.entrySet()) {
            LOG.info(s.getKey() + "====" + s.getValue());
        }

        SchedulerService.INSTANCE.schedule(TaskService.INSTANCE.createTask(Types.REST, jsonObject));

        return null;
    }
}
