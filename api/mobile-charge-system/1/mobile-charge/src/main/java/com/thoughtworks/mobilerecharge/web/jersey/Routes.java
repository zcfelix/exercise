package com.thoughtworks.mobilerecharge.web.jersey;

import com.thoughtworks.mobilerecharge.domain.Card;
import com.thoughtworks.mobilerecharge.domain.Package;

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

    public URI packageUri(String cardId, Package aPackage) {
        return URI.create(String.format("%scards/%s/packages/%s", baseUri, cardId, aPackage.getPackageId()));
    }
}
