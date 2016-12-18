package com.thoughtworks.ybzhou.ioc.bean;

import com.thoughtworks.ybzhou.ioc.AbstractModule;

import java.util.LinkedHashSet;
import java.util.Set;

public class BillingModule implements AbstractModule{

    private Set<Class> boundClasses = new LinkedHashSet<>();

    @Override
    public void bind(Class clazz) {
        boundClasses.add(clazz);
    }

    @Override
    public Set<Class> getBoundClassSet() {
        return boundClasses;
    }
}
