package com.yevheniimakar.factory;

import com.yevheniimakar.annotations.PropertyKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.Properties;


public class Factory<T> {

    private static final Logger log = LoggerFactory.getLogger(Factory.class);

    public T map(Class<T> type, Properties properties) {

        Field[] fieldList = type.getDeclaredFields();
        String propertyName;
        T t = null;
        try {
            t = (T) type.getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            log.error(e.getMessage());
        } catch (IllegalAccessException e) {
            log.error(e.getMessage());
        } catch (InvocationTargetException e) {
            log.error(e.getMessage());
        } catch (NoSuchMethodException e) {
            log.error(e.getMessage());
        }
        for (int i = 0; i < fieldList.length; i++) {
            propertyName = fieldList[i].getAnnotation(PropertyKey.class).value();

            if (properties.containsKey(propertyName)) {
                if (fieldList[i].getType() == int.class || fieldList[i].getType() == Integer.class) {

                    fieldList[i].setAccessible(true);
                    try {
                        fieldList[i].setInt(t, Integer.parseInt(properties.getProperty(propertyName)));
                    } catch (IllegalAccessException illegalAccessException) {
                        log.error(illegalAccessException.getMessage());
                    }
                }
                if (fieldList[i].getType() == String.class) {

                    fieldList[i].setAccessible(true);
                    try {
                        fieldList[i].set(t, properties.getProperty(propertyName));
                    } catch (IllegalAccessException illegalAccessException) {
                        log.error(illegalAccessException.getMessage());
                    }
                }
                if (fieldList[i].getType() == long.class || fieldList[i].getType() == Long.class) {
                    fieldList[i].setAccessible(true);
                    try {
                        fieldList[i].setLong(t, Long.parseLong(properties.getProperty(propertyName)));
                    } catch (IllegalAccessException illegalAccessException) {
                        log.error(illegalAccessException.getMessage());
                    }
                }
                if (fieldList[i].getType() == double.class || fieldList[i].getType() == Double.class) {

                    fieldList[i].setAccessible(true);
                    try {
                        fieldList[i].setDouble(t, Double.parseDouble(properties.getProperty(propertyName)));
                    } catch (IllegalAccessException illegalAccessException) {
                        log.error(illegalAccessException.getMessage());
                    }
                }
                if (fieldList[i].getType() == float.class || fieldList[i].getType() == Float.class) {

                    fieldList[i].setAccessible(true);
                    try {
                        fieldList[i].setFloat(t, Float.parseFloat(properties.getProperty(propertyName)));
                    } catch (IllegalAccessException illegalAccessException) {
                        log.error(illegalAccessException.getMessage());
                    }
                }

                if (fieldList[i].getType() == char.class || fieldList[i].getType() == Character.class) {

                    fieldList[i].setAccessible(true);
                    try {
                        fieldList[i].setChar(t, properties.getProperty(propertyName).charAt(0));
                    } catch (IllegalAccessException illegalAccessException) {
                        log.error(illegalAccessException.getMessage());
                    }
                }

                if (fieldList[i].getType() == LocalDate.class) {

                    String[] local = properties.getProperty(propertyName).split("-");
                    fieldList[i].setAccessible(true);
                    if (local.length == 3) {
                        LocalDate localDate = LocalDate.of(Integer.parseInt(local[0]), Integer.parseInt(local[1]), Integer.parseInt(local[2]));
                        try {
                            fieldList[i].set(t, localDate);
                        } catch (IllegalAccessException illegalAccessException) {
                            log.error(illegalAccessException.getMessage());
                        }
                    } else {
                        try {
                            fieldList[i].set(t, null);
                        } catch (IllegalAccessException illegalAccessException) {
                            log.error(illegalAccessException.getMessage());
                        }
                    }
                }
                if (fieldList[i].getType().isEnum()) {

                    fieldList[i].setAccessible(true);
                    try {
                        fieldList[i].set(t, Enum.valueOf((Class<Enum>) fieldList[i].getType(), properties.getProperty(propertyName)));
                    } catch (IllegalAccessException illegalAccessException) {
                        log.error(illegalAccessException.getMessage());
                    }
                }
                if (fieldList[i].getType() == boolean.class || fieldList[i].getType() == Boolean.class) {

                    fieldList[i].setAccessible(true);
                    try {
                        fieldList[i].setBoolean(t, properties.getProperty(propertyName).equals("true"));
                    } catch (IllegalAccessException illegalAccessException) {
                        log.error(illegalAccessException.getMessage());
                    }
                }
            }
        }

        return t;
    }

}
