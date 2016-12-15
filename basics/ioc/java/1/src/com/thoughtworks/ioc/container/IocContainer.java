package com.thoughtworks.ioc.container;

import com.thoughtworks.ioc.annotation.Bean;
import com.thoughtworks.ioc.annotation.Inject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IocContainer {

    private ClassPathCandidateComponentScanner scanner;

    private Map<Class, Object> objectMap;

    public IocContainer() {
        scanner = new ClassPathCandidateComponentScanner();
        objectMap = new HashMap<>();
    }

    public void init() {
        try {
            Set<Class> candidateComponents = scanner.findCandidateComponents("com.thoughtworks.ioc.bean");
            for (Class klass : candidateComponents) {
                if (klass.isAnnotationPresent(Bean.class)) {
                    Object object = klass.newInstance();

                    Field[] fields = klass.getDeclaredFields();
                    for(Field field : fields) {
                        if (field.isAnnotationPresent(Inject.class)) {
                            Inject inject = field.getAnnotation(Inject.class);
                            if (inject != null) {
                                String name = inject.value();
                                Class fieldClass = Class.forName("com.thoughtworks.ioc.bean." + name);
                                field.setAccessible(true);
                                field.set(object, resolve(fieldClass));
                            }

                        }
                    }
                    objectMap.putIfAbsent(klass, object);
                }
            }
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public Map<Class, Object> getObjectMap() {
        return objectMap;
    }

    public <T> T resolve(Class klass) {
        return (T) objectMap.get(klass);
    }

}
