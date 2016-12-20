package com.thoughtworks.testclass;

import com.thoughtworks.ioc.core.Inject;

public class Car {

    @Inject
    private Seat seat;

    public Seat getSeat() {
        return seat;
    }
}
