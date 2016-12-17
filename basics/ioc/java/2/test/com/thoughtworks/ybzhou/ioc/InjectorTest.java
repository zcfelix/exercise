package com.thoughtworks.ybzhou.ioc;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;

public class InjectorTest {

    private Injector injector;

    @Before
    public void before() {
        injector = new Injector();
        injector.register(CreditCardProcessor.class);
        injector.register(BillingService.class);
    }

    @Test
    public void should_get_instance_from_injector() {
        CreditCardProcessor creditCardProcessor = injector.getInstance(CreditCardProcessor.class);
        System.out.println(creditCardProcessor.getBillingService());
        assertThat(creditCardProcessor.getBillingService(), not(nullValue()));
    }

    @Test(expected = InstantiationError.class)
    public void should_throw_error_non_injected_instance_from_container() throws Exception{
        User user = injector.getInstance(User.class);
        System.out.println(user);
    }

}
