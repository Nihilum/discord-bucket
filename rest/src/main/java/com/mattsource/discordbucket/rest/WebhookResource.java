package com.mattsource.discordbucket.rest;

import com.mattsource.discordbucket.logging.Logger;
import com.mattsource.discordbucket.logging.LoggerFactory;
import com.mattsource.discordbucket.scheduler.SchedulerService;
import com.mattsource.discordbucket.scheduler.TaskService;
import com.mattsource.discordbucket.vocabulary.Types;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.StringReader;

@Path("rest")
public class WebhookResource {
    private static final Logger LOG = LoggerFactory.INSTANCE.getLogger(WebhookResource.class);

    @POST
    @Path("webhook")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response webhook(String body) throws Exception {
        LOG.info("Accessed rest/webhook");

        JsonObject jsonObject = Json.createReader(new StringReader(body)).readObject();

        // Asynchronous, don't wait for result
        SchedulerService.INSTANCE
                .schedule(TaskService.INSTANCE.createTask(Types.TRANSLATION, jsonObject));

        return Response.status(200).entity("OK").build();
    }
}
