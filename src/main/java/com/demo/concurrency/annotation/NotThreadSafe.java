package com.demo.concurrency.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 课程里用来标记线程不安全的类或者写法
 * create by wangzhiqian on 2018/5/13
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface NotThreadSafe {

    String value() default "";
}
