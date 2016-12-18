package com.thoughtworks.ybzhou.ioc;

import com.thoughtworks.ybzhou.ioc.bean.BillingService;
import com.thoughtworks.ybzhou.ioc.bean.CreditCardProcessor;
import com.thoughtworks.ybzhou.ioc.bean.TransactionLog;
import com.thoughtworks.ybzhou.ioc.bean.User;
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
        injector.register(TransactionLog.class);
        injector.register(BillingService.class);
        injector.register(CreditCardProcessor.class);
    }

    @Test
    public void should_get_instance_from_injector() {
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
