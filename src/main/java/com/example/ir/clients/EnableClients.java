package com.example.ir.clients;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(ClientsRegistrar.class)
public @interface EnableClients {

	String[] value() default {};

	String[] basePackages() default {};

	Class<?>[] basePackageClasses() default {};

}
