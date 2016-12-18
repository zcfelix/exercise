package com.thoughtworks.ybzhou.ioc;

import java.util.HashMap;
import java.util.Map;

public class Binder {

    private static Map<Class, Class> classMap = new HashMap<>();

    private Class interfaceClass;

    private Binder(Class interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    public static Binder bind(Class interfaceClass) {
        return new Binder(interfaceClass);
    }

    public Binder to(Class implementClass) {
        classMap.put(interfaceClass, implementClass);
        return this;
    }

    public static Map<Class, Class> getClassMap() {
        return classMap;
    }

    public static void clear() {
        classMap.clear();
    }
}
