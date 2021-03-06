package com.thoughtworks.mobilerecharge.domain;

import java.util.Optional;

public interface Uniqueness<Key, Entity> {
    Optional<Entity> findBy(Key key);
}
