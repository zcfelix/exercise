package com.thoughtworks.ioc.exception;

public class NoInjectedConstructorFoundException extends RuntimeException {
    public NoInjectedConstructorFoundException(Class parameterClass, Class instanceClass) {
        super("no injected constructor of " + parameterClass.getName() + " in class " + instanceClass.getName() + " were found");
    }
}
