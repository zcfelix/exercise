package com.thoughtworks.mobilecharge.api;

import com.thoughtworks.mobilecharge.domain.Card;
import com.thoughtworks.mobilecharge.domain.CardRepository;
import com.thoughtworks.mobilecharge.jersey.Routes;
import com.thoughtworks.mobilecharge.validators.Validator;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

import static com.thoughtworks.mobilecharge.validators.Validators.*;

@Path("cards")
public class CardsApi {

    @Context
    CardRepository cardRepository;

    @Context
    Routes routes;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response initializeCard(Map<String, Object> info) {

        Validator cardValidator = all(fieldNotEmpty("number", "number is required"),
                fieldNotEmpty("balance", "balance is required"),
                unique("number", "number is already existed", cardRepository));

        validate(cardValidator, info);

        Card card = cardRepository.createCard(info);
        return Response.status(201).location(routes.cardUri(card)).build();
    }

    @Path("{cid}")
    public CardApi getCardInfo(@PathParam("cid") long cid) {
        return cardRepository.find(cid)
                .map(CardApi::new)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }

}
