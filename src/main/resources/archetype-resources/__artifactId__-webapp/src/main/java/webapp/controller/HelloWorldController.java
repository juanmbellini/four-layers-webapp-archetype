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

    @Context
    private UriInfo uriInfo;

    @GET
    public Response getUsers() {
        return Response.ok("Hello World!").build();
    }
}
