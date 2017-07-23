package ${package}.webapp.controller;

import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.*;


/**
 * Basic Jersey controller. Add endpoints here.
 */
@Path("hello-world")
@Component
public class HelloWorldController {

    public static final String HELLO_WORLD = "Hello World!";

    @Context
    private UriInfo uriInfo;

    @GET
    public Response getHelloWorld() {
        return Response.ok(HELLO_WORLD).location(uriInfo.getAbsolutePath()).build();
    }
}
