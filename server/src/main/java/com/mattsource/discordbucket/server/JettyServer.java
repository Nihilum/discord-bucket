package com.mattsource.discordbucket.server;

import com.mattsource.discordbucket.config.Config;
import com.mattsource.discordbucket.config.ConfigService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

class JettyServer {

    private final Server server;

    JettyServer() {
        Config discordBucketConfig = ConfigService.INSTANCE.getConfig();

        if (discordBucketConfig == null) {
            throw new IllegalStateException(
                    "Can not start the server without proper configuration file.");
        }

        this.server = new Server(discordBucketConfig.port());

        ResourceConfig config = new ResourceConfig();
        config.packages("com.mattsource.discordbucket.rest");
        ServletHolder servlet = new ServletHolder(new ServletContainer(config));

        ServletContextHandler context = new ServletContextHandler(server, "/*");
        context.addServlet(servlet, "/*");
    }

    void serve() throws Exception {
        try {
            server.start();
            server.join();
        } finally {
            server.destroy();
        }
    }
}
