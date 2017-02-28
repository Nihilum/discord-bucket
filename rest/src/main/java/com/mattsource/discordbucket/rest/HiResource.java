package com.mattsource.discordbucket.rest;

import com.mattsource.discordbucket.logging.Logger;
import com.mattsource.discordbucket.logging.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("hi")
public class HiResource {
    private static final Logger LOG = LoggerFactory.INSTANCE.getLogger(HiResource.class);

    @GET
    @Path("hi")
    @Produces(MediaType.TEXT_PLAIN)
    public String hi() {
        LOG.info("Accessing hi/hi");

        return "Hi everyone!";
    }
}
