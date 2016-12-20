package com.thoughtworks.ioc;

import com.thoughtworks.testclass.Car;
import com.thoughtworks.testclass.MultiAnnotatedCar;
import com.thoughtworks.testclass.ParametersCar;
import com.thoughtworks.testclass.SomeNonInjectParameterCar;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;

public class InjectorTest {
    @Test
    public void should_get_car_from_injector_when_injecting_default_constructor() throws Exception {
        Car car = Injector.getInstance(Car.class);
        assertThat(car, not(nullValue()));
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_runtime_error_when_more_than_one_constructors_are_annotated() throws Exception {
        MultiAnnotatedCar multiAnnotatedCar = Injector.getInstance(MultiAnnotatedCar.class);
    }

    @Test
    public void should_get_car_from_injector_when_injecting_constructor_with_parameters() throws Exception {
        ParametersCar parametersCar = Injector.getInstance(ParametersCar.class);
        assertThat(parametersCar, not(nullValue()));
        assertThat(parametersCar.getDriver(), not(nullValue()));
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_runtime_error_when_some_parameters_not_found_in_injected_constructor() throws Exception {
        SomeNonInjectParameterCar someNonInjectParameterCar = Injector.getInstance(SomeNonInjectParameterCar.class);
    }
}
