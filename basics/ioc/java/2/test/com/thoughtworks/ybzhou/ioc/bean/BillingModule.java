package com.thoughtworks.ybzhou.ioc.bean;

import com.thoughtworks.ybzhou.ioc.AbstractModule;

import java.util.LinkedHashSet;
import java.util.Set;

import static com.thoughtworks.ybzhou.ioc.Binder.bind;

public class BillingModule implements AbstractModule{

    private Set<Class> boundClasses = new LinkedHashSet<>();

    @Override
    public void configure() {
        bind(User.class).to(UserImpl.class);
    }

    @Override
    public void register(Class clazz) {
        boundClasses.add(clazz);
    }

    @Override
    public Set<Class> getBoundClassSet() {
        return boundClasses;
    }
}
