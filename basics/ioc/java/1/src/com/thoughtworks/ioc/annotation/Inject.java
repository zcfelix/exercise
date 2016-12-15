package com.thoughtworks.ioc.annotation;


import java.lang.annotation.*;


@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented

public @interface Inject {
    public String name() default "";
}
