package com.thoughtworks.ybzhou.ioc;

import java.util.Set;

public interface AbstractModule {
    void configure();
    void register(Class clazz);
    Set<Class> getBoundClassSet();
}
