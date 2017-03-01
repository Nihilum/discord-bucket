package com.mattsource.discordbucket.rest;

import java.util.concurrent.Callable;

import javax.json.JsonObject;

import com.mattsource.discordbucket.logging.Logger;
import com.mattsource.discordbucket.logging.LoggerFactory;
import com.mattsource.discordbucket.scheduler.SchedulerTask;
import com.mattsource.discordbucket.vocabulary.Types;

@SchedulerTask(Types.REST)
public class RestTask implements Callable<Void> {
    private static final Logger LOG = LoggerFactory.INSTANCE.getLogger(RestTask.class);

    private final JsonObject jsonObject;

    public RestTask(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    @Override
    public Void call() throws Exception {
        LOG.info("Running REST task.");

        if (jsonObject == null) {
            return null;
        }

        return null;
    }
}
