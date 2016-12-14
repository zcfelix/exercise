package com.thoughtworks.ioc;

public class Foo implements TestAble{
    public Foo() {
    }

    @Override
    public void print() {
        System.out.println("foo");
    }
}
