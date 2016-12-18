package com.thoughtworks.ybzhou.ioc;

import com.thoughtworks.ybzhou.ioc.bean.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;

public class InjectorTest {

    private Injector injector;

    @Before
    public void before() {
        BillingModule billingModule = new BillingModule();
        billingModule.register(BillingService.class);
        billingModule.register(CreditCardProcessor.class);
        billingModule.register(TransactionLog.class);

        injector = Juice.getInjector(billingModule);
    }

    @After
    public void after() {
        Binder.clear();
    }

    @Test
    public void should_get_instance_from_injector_after_registering() {
        BillingService billingService = injector.getInstance(BillingService.class);
        assertThat(billingService.getCreditCardProcessor(), not(nullValue()));
        assertThat(billingService.getTransactionLog(), not(nullValue()));
    }

    @Test(expected = InstantiationError.class)
    public void should_throw_error_non_injected_instance_from_container() throws Exception{
        User user = injector.getInstance(User.class);
        System.out.println(user);
    }

}
