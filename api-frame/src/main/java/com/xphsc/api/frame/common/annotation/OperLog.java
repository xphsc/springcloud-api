package com.xphsc.api.frame.common.annotation;

import java.lang.annotation.*;

/**
 * Created by ${huipei.x} on 2016/8/8.
 * qq群593802274
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface OperLog {
    String value() default "";
    String description()  default "";
}
