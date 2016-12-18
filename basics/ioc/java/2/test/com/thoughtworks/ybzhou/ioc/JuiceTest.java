package com.thoughtworks.ybzhou.ioc;

import com.thoughtworks.ybzhou.ioc.bean.BillingModule;
import com.thoughtworks.ybzhou.ioc.bean.BillingService;
import com.thoughtworks.ybzhou.ioc.bean.CreditCardProcessor;
import com.thoughtworks.ybzhou.ioc.bean.TransactionLog;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;

public class JuiceTest {
    @Test
    public void should_get_injector_from_juice() throws Exception {

        BillingModule billingModule = new BillingModule();
        billingModule.bind(BillingService.class);
        billingModule.bind(CreditCardProcessor.class);
        billingModule.bind(TransactionLog.class);

        Injector injector = Juice.getInjector(billingModule);
        BillingService billingService = injector.getInstance(BillingService.class);
        assertThat(billingService.getCreditCardProcessor(), not(nullValue()));
        System.out.println(billingService.getCreditCardProcessor());
    }
}
