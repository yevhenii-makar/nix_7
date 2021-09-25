package com.yevheniimakar.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention (RetentionPolicy.RUNTIME)
public @interface CsvDb {
    String tableName();
}
