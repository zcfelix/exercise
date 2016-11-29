package com.thoughtworks.mobilecharge.domain;

import java.util.Optional;

public interface Uniqueness<Key, Entity> {
    Optional<Entity> findBy(Key key);
}
