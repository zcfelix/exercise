package com.thoughtworks.testclass;

import com.thoughtworks.ioc.Inject;

public class ParametersCar {
    private Seat driver;

    @Inject
    public ParametersCar(Seat driver) {
        this.driver = driver;
    }

    public Seat getDriver() {
        return driver;
    }

}
