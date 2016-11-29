package com.thoughtworks.mobilerecharge.domain;

import java.util.Map;
import java.util.Optional;

public interface CardRepository extends Uniqueness<String, Card> {
    Card createCard(Map<String, Object> info);

    @Override
    Optional<Card> findBy(String number);

    Optional<Card> find(long cid);

    //    Optional<Card> findByNumber(String number);
}
