package com.thoughtworks.ioc;

import com.thoughtworks.ioc.core.Injector;
import com.thoughtworks.testclass.bind.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

public class InjectorTest {
    private Injector injector;

    @Before
    public void before() {
        injector = new Injector();
        injector.register(AbstractCar.class)
                .register(Car.class)
                .register(CarWithFinalField.class)
                .register(CarWithGenericMethod.class)
                .register(Door.class)
                .register(Driver.class)
                .register(MultiAnnotatedCar.class)
                .register(ParametersCar.class)
                .register(Seat.class)
                .register(SomeNonInjectParameterCar.class);
    }

    @After
    public void after() {
        injector.clear();
    }

    @Test
    public void should_get_car_from_injector_when_injecting_default_constructor() throws Exception {
        Car car = injector.getInstance(Car.class);
        assertThat(car, not(nullValue()));
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_runtime_error_when_more_than_one_constructors_are_annotated() throws Exception {
        injector.getInstance(MultiAnnotatedCar.class);
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_runtime_error_when_no_injected_constructor_found() throws Exception {
        injector.getInstance(Door.class);
    }

    @Test
    public void should_get_car_from_injector_when_injecting_constructor_with_parameters() throws Exception {
        ParametersCar parametersCar = injector.getInstance(ParametersCar.class);
        assertThat(parametersCar, not(nullValue()));
        assertThat(parametersCar.getDriver(), not(nullValue()));
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_runtime_error_when_some_parameters_not_found_in_injected_constructor() throws Exception {
        injector.getInstance(SomeNonInjectParameterCar.class);
    }
    // Test about injecting constructors end.


    @Test(expected = RuntimeException.class)
    public void should_throw_runtime_error_when_any_injected_field_is_final() throws Exception {
        CarWithFinalField car = injector.getInstance(CarWithFinalField.class);
        System.out.println(car.getSeat());
    }

    @Test
    public void should_inject_field_to_an_instance() throws Exception {
        Car car = injector.getInstance(Car.class);
        assertThat(car.getSeat(), not(nullValue()));
    }
    // Test about injecting fields end.


//    @Test(expected = InstantiationException.class)
//    public void should_throw_runtime_error_when_injecting_abstract_method() throws Exception {
//        AbstractCar abstractCar = Injector.getInstance(AbstractCar.class);
//    }


    @Test(expected = RuntimeException.class)
    public void should_throw_error_when_injected_method_include_any_type_parameter() throws Exception {
        injector.getInstance(CarWithGenericMethod.class);
    }

    @Test
    public void should_inject_method_with_parameters_success() throws Exception {
        Car car = injector.getInstance(Car.class);
        assertThat(car.driverInfo().equals("I am the driver"), is(true));
    }

    @Test
    public void should_inject_method_with_no_parameters_success() throws Exception {
        Car car = injector.getInstance(Car.class);
        car.accelerate();
    }
    // Test about injecting methods end.
}
