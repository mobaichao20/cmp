package io.cmp.modules.specialList.config;

import java.lang.annotation.*;

/**
 * 日志自定义注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    String value() default "";
}
