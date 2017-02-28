package com.mattsource.discordbucket.logging;

public enum LoggerFactory {
    INSTANCE;

    public Logger getLogger(Class clazz) {
        return new LoggerImpl(clazz);
    }
}
