package com.thoughtworks.ybzhou.ioc;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

public class Injector {

    private Set<Class> classSet;
    private Map<Class, Class> classMap;

    public Injector(AbstractModule module) {
        classMap = Binder.getClassMap();
        classSet = module.getBoundClassSet();
    }

    private boolean isBound(Class clazz) {
        if (classMap == null || classMap.size() == 0) {
            return false;
        }
        return classMap.containsKey(clazz);
    }

    private boolean isRegistered(Class clazz) {
        if (classSet == null || classSet.size() == 0) {
            return false;
        }
        return classSet.contains(clazz);
    }


    public <T> T getInstance(Class<T> clazz) {
        if (isRegistered(clazz))
            return instantiateFromClassSet(clazz);
        else if (isBound(clazz))
            return instantiateFromClassMap(clazz);
        else
            throw new InstantiationError(clazz.getName() + " not registered or bound yet.");
    }

    private <T> T instantiateFromClassSet(Class<T> clazz) {
        try {
            Field[] fields = clazz.getDeclaredFields();
            Object obj;
            if (classSet.contains(clazz)) {
                obj = clazz.newInstance();
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
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private <T> T instantiateFromClassMap(Class<T> clazz) {
        Class ImplementClass = classMap.get(clazz);
        try {
            return (T) ImplementClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
