package com.thoughtworks.mobilecharge.api;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CardsApiTest extends JerseyTest {
//    CardRepository cardRepository = mock();

    @Override
    protected Application configure() {
        return new ResourceConfig(CardsApi.class);
    }

    @Test
    public void should_return_200_when_initialize_a_card() throws Exception {
        Response response = target("cards").request().post(Entity.json(new HashMap<String, Object>()));
        assertThat(response.getStatus(), is(200));
    }
}
