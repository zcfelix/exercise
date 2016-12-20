package com.thoughtworks.ioc;

import com.thoughtworks.ioc.core.Injector;
import com.thoughtworks.testclass.*;
import org.junit.Test;

import static com.thoughtworks.ioc.core.Injector.getInstance;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;

public class InjectorTest {
    @Test
    public void should_get_car_from_injector_when_injecting_default_constructor() throws Exception {
        Car car = getInstance(Car.class);
        assertThat(car, not(nullValue()));
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_runtime_error_when_more_than_one_constructors_are_annotated() throws Exception {
        MultiAnnotatedCar multiAnnotatedCar = getInstance(MultiAnnotatedCar.class);
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_runtime_error_when_no_injected_constructor_found() throws Exception {
        Door door = getInstance(Door.class);
    }

    @Test
    public void should_get_car_from_injector_when_injecting_constructor_with_parameters() throws Exception {
        ParametersCar parametersCar = getInstance(ParametersCar.class);
        assertThat(parametersCar, not(nullValue()));
        assertThat(parametersCar.getDriver(), not(nullValue()));
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_runtime_error_when_some_parameters_not_found_in_injected_constructor() throws Exception {
        SomeNonInjectParameterCar someNonInjectParameterCar = getInstance(SomeNonInjectParameterCar.class);
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_runtime_error_when_any_injected_field_is_final() throws Exception {
        CarWithFinalField car = Injector.getInstance(CarWithFinalField.class);
        System.out.println(car.getSeat());
    }

    @Test
    public void should_inject_field_to_an_instance() throws Exception {
        Car car = Injector.getInstance(Car.class);
        assertThat(car.getSeat(), not(nullValue()));
    }
}
