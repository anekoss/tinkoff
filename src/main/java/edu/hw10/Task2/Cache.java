package edu.hw10.Task2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface Cache {
    boolean persist() default false;
}
