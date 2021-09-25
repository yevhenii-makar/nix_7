package com.yevheniimakar.annotation;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;


@Retention (RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CsvMapping {
    String value();
}
