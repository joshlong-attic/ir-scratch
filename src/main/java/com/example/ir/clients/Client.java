package com.example.ir.clients;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE;

@Target(TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Indexed
public @interface Client {
}
