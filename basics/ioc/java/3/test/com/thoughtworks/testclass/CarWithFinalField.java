package com.thoughtworks.testclass;

import com.thoughtworks.ioc.core.Inject;

public class CarWithFinalField {

    @Inject
    private final Seat seat;

    {
        seat = null;
    }

    public Seat getSeat() {
        return seat;
    }
}
