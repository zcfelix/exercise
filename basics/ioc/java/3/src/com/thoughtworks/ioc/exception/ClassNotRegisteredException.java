package com.thoughtworks.ioc.exception;

public class ClassNotRegisteredException extends RuntimeException {
    public ClassNotRegisteredException(Class clazz) {
        super(clazz.getName() + " not registered");
    }
}
