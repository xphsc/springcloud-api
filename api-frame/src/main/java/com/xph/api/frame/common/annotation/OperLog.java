package com.xph.api.frame.common.annotation;

import java.lang.annotation.*;

/**
 * Created by ${huipei.x} on 2017-10-30.
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface OperLog {
    String value() default "";
    String description()  default "";
}
