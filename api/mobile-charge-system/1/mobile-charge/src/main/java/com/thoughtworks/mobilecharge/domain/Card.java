package com.thoughtworks.mobilecharge.domain;

import java.math.BigDecimal;

public class Card {
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
}
