package com.thoughtworks.ioc.exception;

public class NoInjectedConstructorFoundException extends RuntimeException {
    public NoInjectedConstructorFoundException(Class clazz) {
        super("constructor of " + clazz.getName() + " were not found");
    }
}
