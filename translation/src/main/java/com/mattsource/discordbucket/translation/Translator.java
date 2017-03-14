package com.mattsource.discordbucket.translation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.mattsource.discordbucket.vocabulary.Parameters;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Translator {
    Parameters value();
}
