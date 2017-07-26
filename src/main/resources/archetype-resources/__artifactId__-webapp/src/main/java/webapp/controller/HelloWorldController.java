package ${package}.webapp.controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.time.LocalTime;


/**
 * Basic Jersey controller. Add endpoints here.
 */
@Path("hello-world")
@Component
public class HelloWorldController {

    public static final String HELLO_WORLD = "Hello World!";

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldController.class);

    @Context
    private UriInfo uriInfo;

    @GET
    public Response getHelloWorld() {
        LOGGER.info("Hello, world. Time is {}", LocalTime.now());
        LOGGER.warn("Hello, world! This is a warning log! Time is {}", LocalTime.now());
        LOGGER.error("Hello, world! This is an error log!! Time is {}", LocalTime.now());
        return Response.ok(HELLO_WORLD).location(uriInfo.getAbsolutePath()).build();
    }
}
