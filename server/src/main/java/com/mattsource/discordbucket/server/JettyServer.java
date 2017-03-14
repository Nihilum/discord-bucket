package com.mattsource.discordbucket.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

class JettyServer {

    private final Server server;

    JettyServer() {
        this.server = new Server(1718);

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
