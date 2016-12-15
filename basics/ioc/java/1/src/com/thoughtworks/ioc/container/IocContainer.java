package com.thoughtworks.ioc.container;

import com.thoughtworks.ioc.annotation.Bean;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IocContainer {

    private ClassPathCandidateComponentScanner scanner;

    private Map<String, Object> objectMap;

    public IocContainer() {
        scanner = new ClassPathCandidateComponentScanner();
        objectMap = new HashMap<>();
    }

    public void init() {
        try {
            Set<Class> candidateComponents = scanner.findCandidateComponents("com.thoughtworks.ioc.bean");
            for(Class klass : candidateComponents) {
                if (klass.isAnnotationPresent(Bean.class)) {
                    Bean bean = (Bean)klass.getAnnotation(Bean.class);
                    String id = bean.value();
                    Object object = klass.newInstance();
                    objectMap.putIfAbsent(id, object);
                }
            }
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Object> getObjectMap() {
        return objectMap;
    }

}
