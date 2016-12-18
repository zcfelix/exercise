package com.thoughtworks.ybzhou.ioc.bean;

import com.thoughtworks.ybzhou.ioc.AbstractModule;

import java.util.Set;

import static com.thoughtworks.ybzhou.ioc.Binder.bind;

public class UserModule implements AbstractModule {

    public UserModule() {
        configure();
    }

    @Override
    public void configure() {
        bind(User.class).to(UserImpl.class);
    }

    @Override
    public void register(Class clazz) {

    }

    @Override
    public Set<Class> getBoundClassSet() {
        return null;
    }
}
