package com.thoughtworks.mobilerecharge.domain;

import com.thoughtworks.mobilerecharge.infrastructure.records.Record;
import com.thoughtworks.mobilerecharge.web.jersey.Routes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Card implements Record {
    private String cardId;
    private String number;
    private BigDecimal balance;
    private List<Package> packages;

    public Card(String cardId, String number, BigDecimal balance) {
        this.cardId = cardId;
        this.number = number;
        this.balance = balance;
        this.packages = new ArrayList<>();
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

    public List<Package> getPackages() {
        return packages;
    }
}
