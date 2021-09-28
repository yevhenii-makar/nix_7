package com.yevheniimakar.mapper;

import com.yevheniimakar.annotation.CsvMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class CsvMapper<E> {

    private static final Logger log = LoggerFactory.getLogger(CsvMapper.class);


    public List<E> getObjectList(Class<?> clazz, List<String[]> data) {

        return data.stream().skip(1).map(s -> {return getObjectFromStringArray(clazz, s, data.get(0));}).collect(Collectors.toList());
    }

    private E getObjectFromStringArray(Class<?> clazz, String[] data, String[] head) {

        Field[] fields = clazz.getDeclaredFields();
        Map<String, Integer> headMap = getMapFromStringArr(head);

        E e = null;
        try {
            e = (E) clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException instantiationException) {
            log.error(instantiationException.getMessage());
        } catch (IllegalAccessException illegalAccessException) {
            log.error(illegalAccessException.getMessage());
        } catch (InvocationTargetException invocationTargetException) {
            log.error(invocationTargetException.getMessage());
        } catch (NoSuchMethodException noSuchMethodException) {
            log.error(noSuchMethodException.getMessage());
        }
        for (int i = 0; i < fields.length; i++) {
            int headIndex = headMap.get(fields[i].getAnnotation(CsvMapping.class).value());
            if (fields[i].getType() == int.class || fields[i].getType() == Integer.class) {

                fields[i].setAccessible(true);
                try {
                    fields[i].setInt(e, Integer.parseInt(data[headIndex]));
                } catch (IllegalAccessException illegalAccessException) {
                    log.error(illegalAccessException.getMessage());
                }
            }
            if (fields[i].getType() == String.class) {

                fields[i].setAccessible(true);
                try {
                    fields[i].set(e, data[headIndex]);
                } catch (IllegalAccessException illegalAccessException) {
                    log.error(illegalAccessException.getMessage());
                }
            }
            if (fields[i].getType() == long.class || fields[i].getType() == Long.class) {
                fields[i].setAccessible(true);
                try {
                    fields[i].setLong(e, Long.parseLong(data[headIndex]));
                } catch (IllegalAccessException illegalAccessException) {
                    log.error(illegalAccessException.getMessage());
                }
            }
            if (fields[i].getType() == double.class || fields[i].getType() == Double.class) {

                fields[i].setAccessible(true);
                try {
                    fields[i].setDouble(e, Double.parseDouble(data[headIndex]));
                } catch (IllegalAccessException illegalAccessException) {
                    log.error(illegalAccessException.getMessage());
                }
            }

            if (fields[i].getType() == char.class || fields[i].getType() == Character.class) {

                fields[i].setAccessible(true);
                try {
                    fields[i].setChar(e, data[headIndex].charAt(0));
                } catch (IllegalAccessException illegalAccessException) {
                    log.error(illegalAccessException.getMessage());
                }
            }

            if (fields[i].getType() == LocalDate.class) {

                String[] local = data[headIndex].split("-");
                fields[i].setAccessible(true);
                if (local.length == 3) {
                    LocalDate localDate = LocalDate.of(Integer.parseInt(local[0]), Integer.parseInt(local[1]), Integer.parseInt(local[2]));
                    try {
                        fields[i].set(e, localDate);
                    } catch (IllegalAccessException illegalAccessException) {
                        log.error(illegalAccessException.getMessage());
                    }
                } else {
                    try {
                        fields[i].set(e, null);
                    } catch (IllegalAccessException illegalAccessException) {
                        log.error(illegalAccessException.getMessage());
                    }
                }
            }
            if (fields[i].getType().isEnum()) {

                fields[i].setAccessible(true);
                try {
                    fields[i].set(e, Enum.valueOf((Class<Enum>) fields[i].getType(), data[headIndex]));
                } catch (IllegalAccessException illegalAccessException) {
                    log.error(illegalAccessException.getMessage());
                }
            }
            if (fields[i].getType() == boolean.class || fields[i].getType() == Boolean.class) {

                fields[i].setAccessible(true);
                try {
                    fields[i].setBoolean(e, data[headIndex].equals("true"));
                } catch (IllegalAccessException illegalAccessException) {
                    log.error(illegalAccessException.getMessage());
                }
            }
        }
        return e;
    }

    private Map<String, Integer> getMapFromStringArr(String[] head) {

        Map<String, Integer> headMap = new HashMap<>();
        for (int i = 0; i < head.length; i++) {
            headMap.put(head[i], i);
        }
        return headMap;
    }

}
