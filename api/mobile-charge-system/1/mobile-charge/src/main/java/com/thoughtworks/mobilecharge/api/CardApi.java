package com.thoughtworks.mobilecharge.api;

import com.thoughtworks.mobilecharge.domain.Card;

import javax.ws.rs.GET;
import javax.ws.rs.core.Response;

public class CardApi {
    private Card card;

    public CardApi(Card card) {
        this.card = card;
    }

    @GET
    public Response getCardInfo() {
        return Response.status(200).build();
    }
}
