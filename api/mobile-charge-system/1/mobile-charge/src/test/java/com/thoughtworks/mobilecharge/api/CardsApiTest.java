package com.thoughtworks.mobilecharge.api;

import com.thoughtworks.mobilecharge.domain.Card;
import com.thoughtworks.mobilecharge.domain.CardRepository;
import com.thoughtworks.mobilecharge.jersey.RoutesFeature;
import com.thoughtworks.mobilecharge.support.TestHelper;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import java.util.Optional;

import static java.math.BigDecimal.valueOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CardsApiTest extends JerseyTest {

    CardRepository cardRepository = mock(CardRepository.class);

    @Override
    protected Application configure() {
        return new ResourceConfig(CardsApi.class)
                .register(RoutesFeature.class)
                .register(new AbstractBinder() {
                    @Override
                    protected void configure() {
                        bind(cardRepository).to(CardRepository.class);
                    }
                });
    }

    @Test
    public void should_return_201_when_initialize_a_card() throws Exception {
        Card card = new Card("1", "18810148054", valueOf(44.55));
        when(cardRepository.findBy(anyString())).thenReturn(Optional.empty());
        when(cardRepository.createCard(anyMap())).thenReturn(card);
        Response response = target("cards").request().post(Entity.json(TestHelper.cardMap("18810148054", valueOf(44.55))));
        assertThat(response.getStatus(), is(201));
        assertThat(response.getLocation().toASCIIString().contains("/cards/1"), is(true));
    }

    @Test
    public void should_return_400_when_initialize_a_existed_card() throws Exception {
        Card card = new Card("1", "18810148054", valueOf(44.55));
        when(cardRepository.findBy(anyString())).thenReturn(Optional.of(card));
        when(cardRepository.createCard(anyMap())).thenReturn(card);
        Response response = target("cards").request().post(Entity.json(TestHelper.cardMap("18810148054", valueOf(44.55))));
        assertThat(response.getStatus(), is(400));
    }

    @Test
    public void should_return_404_when_get_card_information_but_card_is_not_existed() throws Exception {
        when(cardRepository.find(anyLong())).thenReturn(Optional.empty());
        Response response = target("cards/1").request().get();
        assertThat(response.getStatus(), is(404));
    }
}
