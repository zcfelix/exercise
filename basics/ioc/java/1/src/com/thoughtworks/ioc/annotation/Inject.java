package com.thoughtworks.ioc.annotation;


import java.lang.annotation.*;


@Target({ElementType.FIELD, ElementType.METHOD, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented

public @interface Inject {
}
