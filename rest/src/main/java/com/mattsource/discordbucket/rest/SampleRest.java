package com.mattsource.discordbucket.rest;

import com.mattsource.discordbucket.logging.Logger;
import com.mattsource.discordbucket.logging.LoggerFactory;

public class SampleRest {
    private static final Logger LOG = LoggerFactory.INSTANCE.getLogger(SampleRest.class);

    public void run() {
        LOG.info("Running rest...");
    }
}
