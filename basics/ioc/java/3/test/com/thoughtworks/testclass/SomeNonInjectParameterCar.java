package com.thoughtworks.testclass;

import com.thoughtworks.ioc.Inject;

public class SomeNonInjectParameterCar {
    private Seat seat;
    private Door door;

    @Inject
    public SomeNonInjectParameterCar(Seat seat, Door door) {
        this.seat = seat;
        this.door = door;
    }
}
