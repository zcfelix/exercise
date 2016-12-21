package com.thoughtworks.ioc;

import com.thoughtworks.ioc.core.Injector;
import com.thoughtworks.testclass.unbind.BillingService;
import com.thoughtworks.testclass.unbind.CreditCardProcessor;
import com.thoughtworks.testclass.unbind.RealBillingService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

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
        injector.getInstance(CreditCardProcessor.class);
    }

    @Test
    public void should_get_instance_from_injector_after_bind_class() throws Exception {
        injector.register(CreditCardProcessor.class);
        CreditCardProcessor creditCardProcessor = injector.getInstance(CreditCardProcessor.class);
        assertThat(creditCardProcessor, not(nullValue()));
    }

    @Test
    public void should_bind_interface_to_a_implement_class() throws Exception {
        injector.bindWith(BillingService.class, RealBillingService.class);
        BillingService billingService = injector.getInstance(RealBillingService.class);
        assertThat(billingService.charge().equals("real charge"), is(true));
    }
}
