package com.mattsource.discordbucket.scheduler;

import com.mattsource.discordbucket.vocabulary.Types;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SchedulerTask {
    Types value();
}
