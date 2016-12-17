package com.thoughtworks.ybzhou.ioc;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Inject {
}
