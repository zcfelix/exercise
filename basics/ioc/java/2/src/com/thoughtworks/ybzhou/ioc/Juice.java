package com.thoughtworks.ybzhou.ioc;

public final class Juice {
    public static Injector getInjector(AbstractModule module) {
        return new Injector(module);
    }
}
