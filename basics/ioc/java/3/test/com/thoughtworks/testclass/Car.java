package com.thoughtworks.testclass;

import com.thoughtworks.ioc.core.Inject;

public class Car {

    @Inject
    private Seat seat;

    private Driver driver;

    public Seat getSeat() {
        return seat;
    }

    @Inject
    public void addDriver(Driver driver) {
        this.driver = driver;
    }

    @Inject
    public void accelerate() {
        System.out.println("Accelerate now!!!");
    }

    public String driverInfo() {
        return driver.toString();
    }

}
