package com.thoughtworks.ioc.exception;

import java.lang.reflect.Field;

public class FinalFieldInjectedException extends RuntimeException {
    public FinalFieldInjectedException(Field field) {
        super("can not inject final field " + field.getName());
    }
}
