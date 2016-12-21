package com.thoughtworks.testclass.unbind;

public class RealBillingService implements BillingService{
    @Override
    public String charge() {
        return "real charge";
    }
}
