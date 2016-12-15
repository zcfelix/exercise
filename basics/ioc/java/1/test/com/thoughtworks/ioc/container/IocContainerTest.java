package com.thoughtworks.ioc.container;

import com.thoughtworks.ioc.bean.Product;
import com.thoughtworks.ioc.bean.User;
import org.junit.Test;

import java.util.Map;

public class IocContainerTest {
    @Test
    public void should_init_container_success() throws Exception {
        IocContainer container = new IocContainer();
        container.init();
        Map<Class, Object> objectMap = container.getObjectMap();
        for(Map.Entry<Class, Object> entry : objectMap.entrySet()) {
            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
        }

        User user = container.resolve(User.class);
        user.setId("fe");
        System.out.println(user.getId());
        System.out.println(user.getProduct());

        Product product = container.resolve(Product.class);
        product.setName("soap");
        System.out.println(product.getName());
    }

}
