package com.mattsource.discordbucket.scheduler;

import com.mattsource.discordbucket.vocabulary.Types;
import org.reflections.Reflections;

import javax.json.JsonObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

public enum TaskService {
    INSTANCE;

    private final Map<Types, Class<?>> schedulerTasks;
    private final Class[] taskCtorArgs;

    TaskService() {
        this.schedulerTasks = initializeTasks();
        this.taskCtorArgs = new Class[1];
        this.taskCtorArgs[0] = JsonObject.class;
    }

    @SuppressWarnings("unchecked")
    public Callable<Void> createTask(Types taskType, JsonObject jsonObject) throws Exception {
        Class<?> clazz = schedulerTasks.get(taskType);

        if (clazz == null) {
            throw new IllegalArgumentException("Missing taskType " + taskType.toString());
        }

        return (Callable<Void>) clazz.getDeclaredConstructor(taskCtorArgs).newInstance(jsonObject);
    }

    private Map<Types, Class<?>> initializeTasks() {
        Map<Types, Class<?>> tasks = new HashMap<>();

        Reflections reflections = new Reflections("com.mattsource.discordbucket");

        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(SchedulerTask.class);

        for (Class<?> clazz : classes) {
            SchedulerTask schedulerTask = clazz.getAnnotation(SchedulerTask.class);

            if (schedulerTask != null) {
                tasks.put(schedulerTask.value(), clazz);
            }
        }

        return tasks;
    }
}
