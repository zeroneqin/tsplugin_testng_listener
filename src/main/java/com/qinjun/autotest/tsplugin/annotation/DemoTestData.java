package com.qinjun.autotest.tsplugin.annotation;

import jdk.nashorn.internal.ir.annotations.Reference;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface DemoTestData {
    String value() default "";
}
