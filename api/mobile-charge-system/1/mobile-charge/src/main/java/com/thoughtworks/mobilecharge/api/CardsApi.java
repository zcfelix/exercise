package com.thoughtworks.mobilecharge.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("cards")
public class CardsApi {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getCardInitializeInfo() {
        return Response.status(200).build();
    }
}
