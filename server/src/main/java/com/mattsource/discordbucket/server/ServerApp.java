package com.mattsource.discordbucket.server;

import com.mattsource.discordbucket.logging.Logger;
import com.mattsource.discordbucket.logging.LoggerFactory;

public class ServerApp {
    private static final Logger LOG = LoggerFactory.INSTANCE.getLogger(ServerApp.class);

    private final JettyServer jettyServer;

    private ServerApp() {
        this.jettyServer = new JettyServer();
    }

    private void run() throws Exception {
        jettyServer.serve();
    }

    public static void main(String[] args) {
        try {
            ServerApp serverApp = new ServerApp();
            serverApp.run();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
