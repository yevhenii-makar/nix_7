package com.yevheniimakar.factory;

import com.yevheniimakar.annotations.PropertyKey;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.Properties;


public class Factory<T> {


    public T map(Class<T> type, Properties properties) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {

        Field[] fieldList = type.getDeclaredFields();
        Class<?> fieldType;
        Field field;
        String propertyName;
        T t = null;

        t = (T) type.getDeclaredConstructor().newInstance();

        for (int i = 0; i < fieldList.length; i++) {
            propertyName = fieldList[i].getAnnotation(PropertyKey.class).value();
            fieldType = fieldList[i].getType();
            field = fieldList[i];

            if (properties.containsKey(propertyName)) {
                if (fieldType == int.class || fieldType == Integer.class) {
                    field.setInt(t, Integer.parseInt(properties.getProperty(propertyName)));
                    field.setAccessible(true);
                } else if (fieldType == String.class) {
                    field.set(t, properties.getProperty(propertyName));
                } else if (fieldType == long.class || fieldType == Long.class) {
                    field.setLong(t, Long.parseLong(properties.getProperty(propertyName)));
                } else if (fieldType == double.class || fieldType == Double.class) {
                    field.setDouble(t, Double.parseDouble(properties.getProperty(propertyName)));
                } else if (fieldType == float.class || fieldType == Float.class) {
                    field.setFloat(t, Float.parseFloat(properties.getProperty(propertyName)));
                } else if (fieldType == char.class || fieldType == Character.class) {
                    field.setChar(t, properties.getProperty(propertyName).charAt(0));
                } else if (fieldType == LocalDate.class) {
                    String[] local = properties.getProperty(propertyName).split("-");
                    if (local.length == 3) {
                        LocalDate localDate = LocalDate.of(Integer.parseInt(local[0]), Integer.parseInt(local[1]), Integer.parseInt(local[2]));
                        field.set(t, localDate);
                    } else {
                        field.set(t, null);
                    }
                }
            }
        }
        return t;
    }

}

