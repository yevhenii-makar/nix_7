package com.yevhenii.makar.annotation;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RunTask {
    String runTaskName();
    int order();
}
