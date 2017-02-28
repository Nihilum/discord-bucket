package com.mattsource.discordbucket.server;

import com.mattsource.discordbucket.logging.SampleLogging;
import com.mattsource.discordbucket.rest.SampleRest;
import com.mattsource.discordbucket.translation.SampleTranslation;

public class ServerApp {
    private final SampleLogging sampleLogging;
    private final SampleRest sampleRest;
    private final SampleTranslation sampleTranslation;

    private ServerApp() {
        this.sampleLogging = new SampleLogging();
        this.sampleRest = new SampleRest();
        this.sampleTranslation = new SampleTranslation();
    }

    private void run() {
        sampleLogging.run();
        sampleRest.run();
        sampleTranslation.run();
    }

    public static void main(String[] args) {
        ServerApp serverApp = new ServerApp();
        serverApp.run();
    }
}
