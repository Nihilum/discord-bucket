package com.mattsource.discordbucket.scheduler;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public enum SchedulerService {
    INSTANCE;

    private final ExecutorService executor;

    SchedulerService() {
        this.executor = Executors.newWorkStealingPool();
    }

    public void schedule(Callable<Void> task) {
        executor.submit(task);
    }
}
