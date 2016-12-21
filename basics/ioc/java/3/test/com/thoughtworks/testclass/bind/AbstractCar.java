package com.thoughtworks.testclass.bind;

import com.thoughtworks.ioc.core.Inject;

public abstract class AbstractCar {

    @Inject
    public abstract void accelerate();
}
