package com.thoughtworks.ybzhou.ioc;

import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Injector {

    private Map<Class, Object> objectMap;
    private Set<Class<?>> classSet;

    public void register(Class<?> klass) {
        classSet.add(klass);
    }

    public Injector() {
        classSet = new LinkedHashSet<>();
    }

    public <T> T getInstance(Class<T> klass) {
        if (!classSet.contains(klass))
            throw new InstantiationError(klass.getName() + " not registered, please register first!");

        try {
            Field[] fields = klass.getDeclaredFields();
            Object obj;
            if (classSet.contains(klass)) {
                obj = klass.newInstance();
                for (Field f : fields) {
                    if (f.isAnnotationPresent(Inject.class)) {
                        f.setAccessible(true);
                        if (classSet.contains(f.getType())) {
                            f.set(obj, f.getType().newInstance());
                        } else throw new InstantiationError(f.getName() + " should be register first.");
                    }
                }
                return (T) obj;
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
