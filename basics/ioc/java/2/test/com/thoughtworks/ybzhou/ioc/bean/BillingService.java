package com.thoughtworks.ybzhou.ioc.bean;

import com.thoughtworks.ybzhou.ioc.Inject;

public class BillingService {

    @Inject
    private CreditCardProcessor creditCardProcessor;

    @Inject
    private TransactionLog transactionLog;

    public BillingService() {
    }

    public BillingService(CreditCardProcessor creditCardProcessor, TransactionLog transactionLog) {
        this.creditCardProcessor = creditCardProcessor;
        this.transactionLog = transactionLog;
    }

    public CreditCardProcessor getCreditCardProcessor() {
        return creditCardProcessor;
    }

    public TransactionLog getTransactionLog() {
        return transactionLog;
    }
}
