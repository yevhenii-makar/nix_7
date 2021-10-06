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

        return data.stream().skip(1).map(s -> {return getObjectFromStringArrayWithTrow(clazz, s, data.get(0));}).collect(Collectors.toList());
    }

    private E getObjectFromStringArrayWithTrow(Class<?> clazz, String[] data, String[] head) {
        E e = null;
        try {
            e= getObjectFromStringArray(clazz, data, head);
        } catch (IllegalAccessException illegalAccessException) {
            log.error(illegalAccessException.getMessage());
        } catch (NoSuchMethodException noSuchMethodException) {
            log.error(noSuchMethodException.getMessage());
        } catch (InvocationTargetException invocationTargetException) {
            log.error(invocationTargetException.getMessage());
        } catch (InstantiationException instantiationException) {
            log.error(instantiationException.getMessage());
        }
        return e;
    }

    private E getObjectFromStringArray(Class<?> clazz, String[] data, String[] head) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {

        Field[] fields = clazz.getDeclaredFields();
        Class<?> fieldType;
        Field field;
        Map<String, Integer> headMap = getMapFromStringArr(head);

        E e = null;

        e = (E) clazz.getDeclaredConstructor().newInstance();

        for (int i = 0; i < fields.length; i++) {
            fieldType = fields[i].getType();
            field = fields[i];
            field.setAccessible(true);
            int headIndex = headMap.get(field.getAnnotation(CsvMapping.class).value());
            if (fieldType == int.class || fieldType == Integer.class) {
                field.setInt(e, Integer.parseInt(data[headIndex]));
            } else if (fieldType == String.class) {
                field.set(e, data[headIndex]);
            } else if (fieldType == long.class || fieldType == Long.class) {
                field.setLong(e, Long.parseLong(data[headIndex]));
            } else if (fieldType == double.class || fieldType == Double.class) {
                field.setDouble(e, Double.parseDouble(data[headIndex]));
            } else if (fieldType == char.class || fieldType == Character.class) {
                field.setChar(e, data[headIndex].charAt(0));
            } else if (fieldType == LocalDate.class) {
                String[] local = data[headIndex].split("-");
                if (local.length == 3) {
                    LocalDate localDate = LocalDate.of(Integer.parseInt(local[0]), Integer.parseInt(local[1]), Integer.parseInt(local[2]));
                    field.set(e, localDate);
                } else {
                    field.set(e, null);
                }
            } else if (fieldType.isEnum()) {
                
                field.set(e, Enum.valueOf((Class<Enum>) fieldType, data[headIndex]));
            } else if (fieldType == boolean.class || fieldType == Boolean.class) {
                
                field.setBoolean(e, data[headIndex].equals("true"));
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
