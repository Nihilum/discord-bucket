package com.mattsource.discordbucket.vocabulary;

public enum Objects {
    ACTOR("actor"),
    PUSH("push"),
    REPOSITORY("repository"),
    CONTENT("content"),
    USERNAME("username"),
    EMBEDS("embeds"),
    EVENT("event"),
    CHANGES("changes"),
    COMMITS("commits"),
    MESSAGE("message"),
    LINKS("links"),
    HTML("html"),
    HREF("href");

    private final String value;

    Objects(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
