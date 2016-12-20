package com.thoughtworks.ioc.exception;

import java.lang.reflect.Method;

public class AbstractMethodInjectionException extends RuntimeException {

    public AbstractMethodInjectionException(Method method) {
        super("abstract method " + method.getName() + " can not be injected");
    }
}
