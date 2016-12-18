package com.thoughtworks.ybzhou.ioc;

import com.thoughtworks.ybzhou.ioc.bean.User;
import com.thoughtworks.ybzhou.ioc.bean.UserModule;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BinderTest {

    private Injector injector;

    @Before
    public void before() {
        injector = Juice.getInjector(new UserModule());
    }

    @After
    public void after() {
        Binder.clear();
    }

    @Test
    public void should_get_object_after_binding() throws Exception {
        User user = injector.getInstance(User.class);
        assertThat(user.talk().equals("hello"), is(true));
    }

}
