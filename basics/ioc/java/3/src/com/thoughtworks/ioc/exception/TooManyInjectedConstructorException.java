package com.thoughtworks.ioc.exception;

public class TooManyInjectedConstructorException extends RuntimeException {

    public TooManyInjectedConstructorException(Class clazz) {
        super("more than 1 injected constructors in " + clazz.getName() + " were found");
    }
}
