package com.thoughtworks.ioc;

import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import static javafx.scene.input.KeyCode.F;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ClassTest {

    @Test
    public void test_class_object() {
        Foo foo = new Foo();

        Class c1 = foo.getClass();
        Class c2 = Foo.class;
        Class c3 = null;
        try {
            c3 = Class.forName("com.thoughtworks.ioc.Foo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        assertThat(c1.equals(c2), is(true));
        assertThat(c1.equals(c3), is(true));


        try {
            Foo newFoo = (Foo)c3.newInstance();
            newFoo.print();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void should_load_class() {
        String which = "Foo";
        try {
            Class klass = Class.forName(String.format("com.thoughtworks.ioc.%s", which));
            TestAble ta = (TestAble)klass.newInstance();
            ta.print();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void getClassInfo(Object o) {
        Class classType = o.getClass();
        System.out.println("类的名称是: " + classType.getName());

        Method[] declaredMethods = classType.getDeclaredMethods();
        for (int i = 0; i < declaredMethods.length; i++) {
            Class returnType = declaredMethods[i].getReturnType();
            System.out.print(returnType.getSimpleName() + " ");

            System.out.print(declaredMethods[i].getName() + "(");

            Parameter[] parameters = declaredMethods[i].getParameters();
            for (int j = 0; j < parameters.length; j++) {
                System.out.print(parameters[j].getType().getSimpleName() + ", ");
            }
            System.out.print(")\n");
        }
    }

    @Test
    public void should_get_methods() {
        getClassInfo(new Bar());
    }
}
