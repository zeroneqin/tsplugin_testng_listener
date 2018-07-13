package com.qinjun.autotest.tsplugin.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface DemoUrl  {
    String value() default "";
}
