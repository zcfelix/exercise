package com.thoughtworks.mobilerecharge.domain;

import com.thoughtworks.mobilerecharge.infrastructure.records.Record;
import com.thoughtworks.mobilerecharge.web.jersey.Routes;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Card implements Record {
    private String cardId;
    private String number;
    private BigDecimal balance;

    public Card(String cardId, String number, BigDecimal balance) {
        this.cardId = cardId;
        this.number = number;
        this.balance = balance;
    }

    public String getCardId() {
        return cardId;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return toJson(routes);
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return new HashMap<String, Object>(){{
            put("uri", routes.cardUri(Card.this));
            put("id", cardId);
            put("number", number);
            put("balance", balance);
        }};
    }
}
