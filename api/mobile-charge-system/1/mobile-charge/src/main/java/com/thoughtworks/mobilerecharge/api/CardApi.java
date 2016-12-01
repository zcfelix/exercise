package com.thoughtworks.mobilerecharge.api;

import com.thoughtworks.mobilerecharge.domain.Card;
import com.thoughtworks.mobilerecharge.domain.CardRepository;
import com.thoughtworks.mobilerecharge.domain.Package;
import com.thoughtworks.mobilerecharge.domain.Policy;
import com.thoughtworks.mobilerecharge.domain.validators.Validator;
import com.thoughtworks.mobilerecharge.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
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

    @GET
    @Path("policy")
    public Policy getPolicy(@Context CardRepository cardRepository) {
        return card.getPolicy();
    }

    @POST
    @Path("recharges")
    public Response recharge(Map<String, Object> info) {

        Validator rechargeValidator = all(fieldNotEmpty("amount", "amount is required"),
                fieldNotEmpty("date", "date is required"));

        validate(rechargeValidator, info);
        card.recharge(new BigDecimal((double)info.get("amount")));
        return Response.status(201).build();
//        return Response.status(201).location(routes.rechargeUri(card.getCardId(), rechargeId)).build();
    }

    @GET
    @Path("recharges")
    public Response listRecharges() {
        return Response.status(200).build();
    }
}
