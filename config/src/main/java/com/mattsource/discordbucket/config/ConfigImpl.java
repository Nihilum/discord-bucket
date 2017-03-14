package com.mattsource.discordbucket.config;

class ConfigImpl implements Config {
    private final int port;
    private final String webHook;
    private final String userAgent;

    ConfigImpl(int port, String webHook, String userAgent) {
        this.port = port;
        this.webHook = webHook;
        this.userAgent = userAgent;
    }

    @Override
    public int port() {
        return port;
    }

    @Override
    public String webHook() {
        return webHook;
    }

    @Override
    public String userAgent() {
        return userAgent;
    }
}
