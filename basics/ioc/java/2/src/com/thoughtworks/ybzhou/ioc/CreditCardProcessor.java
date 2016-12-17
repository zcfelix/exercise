package com.thoughtworks.ybzhou.ioc;

public class CreditCardProcessor {

    @Inject
    private BillingService billingService;

    public CreditCardProcessor() {
    }

//    public void setBillingService(BillingService billingService) {
//        this.billingService = billingService;
//    }

    public BillingService getBillingService() {
        return billingService;
    }
}
