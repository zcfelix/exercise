package com.thoughtworks.ioc.container;

import com.thoughtworks.ioc.bean.Product;
import com.thoughtworks.ioc.bean.User;
import org.junit.Test;

public class IocContainerTest {
    @Test
    public void should_init_container_success() throws Exception {
        IocContainer container = new IocContainer();
        container.init();

        User user = container.resolve(User.class);
        user.setId("fe");
        user.getProduct().setName("soap");
        System.out.println(user.getId());
        System.out.println(user.getProduct());

        Product product = container.resolve(Product.class);
        product.setName("apple");
        System.out.println(product.getName());

        System.out.println(user.getProduct());
    }

}
