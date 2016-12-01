package com.thoughtworks.mobilerecharge.api;

import com.thoughtworks.mobilerecharge.domain.Card;
import com.thoughtworks.mobilerecharge.domain.CardRepository;
import com.thoughtworks.mobilerecharge.domain.Package;
import com.thoughtworks.mobilerecharge.domain.validators.Validator;
import com.thoughtworks.mobilerecharge.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

import static com.thoughtworks.mobilerecharge.domain.validators.Validators.*;

public class CardApi {
    private Card card;

    @Context
    Routes routes;

    public CardApi(Card card) {
        this.card = card;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Card getCardInfo() {
        return card;
    }

    @POST
    @Path("packages")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPackage(Map<String, Object> info) {
        Validator cardValidator = all(fieldNotEmpty("package_id", "package id is required"),
                fieldNotEmpty("effective_date", "effective date is required"));

        validate(cardValidator, info);
        return Response.status(201).build();
    }

    @GET
    @Path("packages")
    public Response listPackages(@Context CardRepository cardRepository) {
        List<Package> packages = card.getPackages();
        return Response.status(200).build();
    }
}
