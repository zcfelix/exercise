package com.thoughtworks.mobilerecharge.api;

import com.thoughtworks.mobilerecharge.domain.Card;
import com.thoughtworks.mobilerecharge.domain.CardRepository;
import com.thoughtworks.mobilerecharge.domain.validators.Validator;
import com.thoughtworks.mobilerecharge.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

import static com.thoughtworks.mobilerecharge.domain.validators.Validators.*;

@Path("cards")
public class CardsApi {


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response initializeCard(Map<String, Object> info,
                                   @Context CardRepository cardRepository,
                                   @Context Routes routes) {

        Validator cardValidator = all(fieldNotEmpty("number", "number is required"),
                fieldNotEmpty("balance", "balance is required"),
                unique("number", "number is already existed", cardRepository));

        validate(cardValidator, info);

        Card card = cardRepository.createCard(info);
        return Response.status(201).location(routes.cardUri(card)).build();
    }

    @Path("{cid}")
    public CardApi getCardInfo(@PathParam("cid") long cid,
                               @Context CardRepository cardRepository) {
        return cardRepository.find(cid)
                .map(CardApi::new)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }

}
