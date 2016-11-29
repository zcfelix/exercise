package com.thoughtworks.mobilecharge.jersey;

import com.thoughtworks.mobilecharge.domain.Card;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

public class Routes {

    private final String baseUri;

    public Routes(UriInfo uriInfo) {
        baseUri = uriInfo.getBaseUri().toASCIIString();
    }

    public URI cardUri(Card card) {
        return URI.create(String.format("%scards/%s", baseUri, card.getCardId()));
    }
}


