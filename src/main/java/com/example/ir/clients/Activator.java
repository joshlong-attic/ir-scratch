package com.example.ir.clients;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE;

@Target({TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Activator {
}