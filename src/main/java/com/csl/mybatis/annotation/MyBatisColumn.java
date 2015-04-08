package com.csl.mybatis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface MyBatisColumn {
	String name() default "";

	boolean isAutoKey() default false;

	boolean isID() default false;

	boolean isInsertIngore() default false;
}
