package com.thoughtworks.testclass;

import com.thoughtworks.ioc.core.Inject;

public class CarWithGenericMethod {

    @Inject
    public <T, V> T echo (T value1, V value2, int value3) {
        return value1;
    }
}
