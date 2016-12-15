package com.thoughtworks.ioc.container;

import org.junit.Test;

import java.util.Map;

public class IocContainerTest {
    @Test
    public void should_init_container_success() throws Exception {
        IocContainer container = new IocContainer();
        container.init();
        Map<String, Object> objectMap = container.getObjectMap();
        for(Map.Entry<String, Object> entry : objectMap.entrySet()) {
            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
        }
    }
}
