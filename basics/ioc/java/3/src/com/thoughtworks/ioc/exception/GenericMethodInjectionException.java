package com.thoughtworks.ioc.exception;

import java.lang.reflect.Method;

public class GenericMethodInjectionException extends RuntimeException {
    public GenericMethodInjectionException(Method method) {
        super("generic method " + method.getName() + " can not be injected");
    }
}
