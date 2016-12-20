package com.thoughtworks.testclass;

import com.thoughtworks.ioc.core.Inject;

public class MultiAnnotatedCar {
    private int capacity;

    @Inject
    public MultiAnnotatedCar() {
    }

    @Inject
    public MultiAnnotatedCar(int capacity) {
        this.capacity = capacity;
    }
}
