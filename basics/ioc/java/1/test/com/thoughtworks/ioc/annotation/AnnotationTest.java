package com.thoughtworks.ioc.annotation;

import com.thoughtworks.ioc.bean.User;
import com.thoughtworks.ioc.container.IocContainer;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AnnotationTest {

    @Test
    public void should_instantiation_object_from_injector() {
        User user = (User) IocContainer.getInstance(User.class);
        System.out.println(user.getClass());
    }
}
