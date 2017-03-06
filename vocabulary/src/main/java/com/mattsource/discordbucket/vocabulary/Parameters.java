package com.mattsource.discordbucket.vocabulary;

public enum Parameters {
    PUSH("push");

    private final String value;

    Parameters(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
