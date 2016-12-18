package com.thoughtworks.ybzhou.ioc;

import java.util.Set;

public interface AbstractModule {
    void bind(Class clazz);
    Set<Class> getBoundClassSet();
}
