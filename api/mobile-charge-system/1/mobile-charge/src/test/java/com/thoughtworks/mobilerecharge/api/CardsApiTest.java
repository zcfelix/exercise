package com.thoughtworks.mobilerecharge.api;

import com.thoughtworks.mobilerecharge.domain.Card;
import com.thoughtworks.mobilerecharge.support.ApiSupport;
import com.thoughtworks.mobilerecharge.support.ApiTestRunner;
import com.thoughtworks.mobilerecharge.support.TestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.Optional;

import static java.math.BigDecimal.valueOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

@RunWith(ApiTestRunner.class)
public class CardsApiTest extends ApiSupport {

//    CardRepository cardRepository = mock(CardRepository.class);

//    @Override
//    protected Application configure() {
//        return new ResourceConfig(CardsApi.class)
//                .register(RoutesFeature.class)
//                .register(RecordWriter.class)
//                .register(new AbstractBinder() {
//                    @Override
//                    protected void configure() {
//                        bind(cardRepository).to(CardRepository.class);
//                    }
//                });
//    }


    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
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
    public void should_return_404_when_get_card_but_card_is_not_existed() throws Exception {
        when(cardRepository.find(anyLong())).thenReturn(Optional.empty());
        Response response = target("cards/1").request().get();
        assertThat(response.getStatus(), is(404));
    }

    @Test
    public void should_return_200_and_information_when_get_card() throws Exception {
        Card card = new Card("1", "18810148054", valueOf(44.55));
        when(cardRepository.find(anyLong())).thenReturn(Optional.of(card));
        Response response = target("cards/1").request().get();

        final Map<String, Object> info = response.readEntity(Map.class);
        assertThat(response.getStatus(), is(200));
        assertThat(info.get("uri").toString().contains("/cards/1"), is(true));
    }
}
