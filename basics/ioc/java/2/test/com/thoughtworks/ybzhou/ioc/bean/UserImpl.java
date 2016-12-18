package com.thoughtworks.ybzhou.ioc.bean;

import com.thoughtworks.ybzhou.ioc.Provider;

public class UserImpl implements User, Provider<User> {

    public UserImpl() {
    }

    @Override
    public String talk() {
        return "hello";
    }

    @Override
    public User get() {
        return new UserImpl();
    }
}
