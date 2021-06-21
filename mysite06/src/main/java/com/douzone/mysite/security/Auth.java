package com.douzone.mysite.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE}) // TYPE를 해놔야 클래스에서도 @auth를 사용 가능
@Retention(RetentionPolicy.RUNTIME) 
public @interface Auth {
	// public String value() default "";
	public String role() default "USER";
	public boolean test() default false;
	
}
