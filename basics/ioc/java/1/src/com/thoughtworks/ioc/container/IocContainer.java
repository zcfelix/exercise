package com.thoughtworks.ioc.container;

import java.util.HashMap;
import java.util.Map;

public class IocContainer {

    private Map<String, Object> beansInstanceMap = new HashMap<>();

    public void bind(String packageName) {

    }

    public static Object getInstance(Class classType) {
        Object o = null;
        try {
            o = classType.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return o;
    }

    public static void getContext() {

    }
}
