package com.thoughtworks.ioc;

import com.thoughtworks.ioc.core.Injector;
import com.thoughtworks.testclass.unbind.CreditCardProcessor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BinderTest {

    private Injector injector;

    @Before
    public void before() {
        injector = new Injector();
    }

    @After
    public void after() {
        injector.clear();
    }

    @Test(expected = RuntimeException.class)
    public void should_throw_error_when_class_not_found() throws Exception {
        CreditCardProcessor creditCardProcessor = injector.getInstance(CreditCardProcessor.class);
    }

    @Test
    public void should_get_instance_from_injector_after_bind_class() throws Exception {
    }
}
