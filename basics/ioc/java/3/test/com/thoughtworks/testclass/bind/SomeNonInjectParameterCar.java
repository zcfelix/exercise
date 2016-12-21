package com.thoughtworks.testclass.bind;

import com.thoughtworks.ioc.core.Inject;

public class SomeNonInjectParameterCar {
    private Seat seat;
    private Door door;

    @Inject
    public SomeNonInjectParameterCar(Seat seat, Door door) {
        this.seat = seat;
        this.door = door;
    }
}
