package com.thoughtworks.ioc;

import com.thoughtworks.ioc.core.Injector;
import com.thoughtworks.testclass.*;
import org.junit.Test;

import static com.thoughtworks.ioc.core.Injector.getInstance;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

public class InjectorTest {
    @Test
    public void should_get_car_from_injector_when_injecting_default_constructor() throws Exception {
        Car car = getInstance(Car.class);
        assertThat(car, not(nullValue()));
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_runtime_error_when_more_than_one_constructors_are_annotated() throws Exception {
        getInstance(MultiAnnotatedCar.class);
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_runtime_error_when_no_injected_constructor_found() throws Exception {
        getInstance(Door.class);
    }

    @Test
    public void should_get_car_from_injector_when_injecting_constructor_with_parameters() throws Exception {
        ParametersCar parametersCar = getInstance(ParametersCar.class);
        assertThat(parametersCar, not(nullValue()));
        assertThat(parametersCar.getDriver(), not(nullValue()));
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_runtime_error_when_some_parameters_not_found_in_injected_constructor() throws Exception {
        getInstance(SomeNonInjectParameterCar.class);
    }
    // Test about injecting constructors end.


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
    // Test about injecting fields end.


//    @Test(expected = InstantiationException.class)
//    public void should_throw_runtime_error_when_injecting_abstract_method() throws Exception {
//        AbstractCar abstractCar = Injector.getInstance(AbstractCar.class);
//    }


    @Test(expected = RuntimeException.class)
    public void should_throw_error_when_injected_method_include_any_type_parameter() throws Exception {
        Injector.getInstance(CarWithGenericMethod.class);
    }

    @Test
    public void should_inject_method_with_parameters_success() throws Exception {
        Car car = Injector.getInstance(Car.class);
        assertThat(car.driverInfo().equals("I am the driver"), is(true));
    }

    @Test
    public void should_inject_method_with_no_parameters_success() throws Exception {
        Car car = Injector.getInstance(Car.class);
        car.accelerate();
    }
    // Test about injecting methods end.
}
