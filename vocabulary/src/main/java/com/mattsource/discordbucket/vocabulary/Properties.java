package com.mattsource.discordbucket.vocabulary;

public enum Properties {
    PORT("port"),
    WEBHOOK("webHook"),
    USERAGENT("userAgent");

    private final String property;

    Properties(String property) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }

}
