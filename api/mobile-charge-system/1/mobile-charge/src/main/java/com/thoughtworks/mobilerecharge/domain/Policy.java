package com.thoughtworks.mobilerecharge.domain;

import com.thoughtworks.mobilerecharge.infrastructure.records.Record;
import com.thoughtworks.mobilerecharge.web.jersey.Routes;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Policy implements Record {
    private BigDecimal priceForCall;
    private BigDecimal priceForMessage;
    private BigDecimal priceForDataUsage;

    public Policy(BigDecimal priceForCall, BigDecimal priceForMessage, BigDecimal priceForDataUsage) {
        this.priceForCall = priceForCall;
        this.priceForMessage = priceForMessage;
        this.priceForDataUsage = priceForDataUsage;
    }

    public BigDecimal getPriceForCall() {
        return priceForCall;
    }

    public BigDecimal getPriceForMessage() {
        return priceForMessage;
    }

    public BigDecimal getPriceForDataUsage() {
        return priceForDataUsage;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return toJson(routes);
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return new HashMap<String, Object>() {{
            put("price_for_call", priceForCall);
            put("price_for_message", priceForMessage);
            put("price_for_data_usage", priceForDataUsage);
        }};
    }
}
