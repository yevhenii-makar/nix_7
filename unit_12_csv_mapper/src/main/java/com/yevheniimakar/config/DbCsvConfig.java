package com.yevheniimakar.config;

import com.yevheniimakar.annotation.CsvDb;
import com.yevheniimakar.annotation.CsvDbAutoIncrement;
import com.yevheniimakar.annotation.CsvMapping;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class DbCsvConfig<E> {

    public void saveDB(List<String[]> db, Field[] fields, String dbPath) {

        createDbField(dbPath, fields, Integer.parseInt(getAutoIncrement(dbPath)));

        try (FileWriter fileWriter = new FileWriter(dbPath, true); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            for (String[] line : db) {
                bufferedWriter.newLine();
                bufferedWriter.write(getStringData(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<E> getAll(Class<?> clazz) {
        String dbPath = clazz.getAnnotation(CsvDb.class).tableName() + ".csv";
        List<String[]> db = getCsvDb(dbPath);
        List<E> eList = db.stream().skip(3).map(d -> {return getObjectFromStringArray(clazz, d);}).collect(Collectors.toList());
        return eList;
    }

    public List<E> getByField(String fieldValue, String fieldName, Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        int fieldPosition = 0;
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].equals(fieldName)) {
                fieldPosition = i;
            }
        }
        String dbPath = clazz.getAnnotation(CsvDb.class).tableName() + ".csv";
        if (!isDbFileExist(dbPath)) {
            createDbField(dbPath, fields, 1);
        }
        String[] head = getHead(dbPath);
        int finalFieldPosition = fieldPosition;
        return this.getCsvDb(dbPath).stream().skip(3).filter(s -> s[finalFieldPosition].equals(fieldValue)).map(d -> {return getObjectFromStringArray(clazz, d);}).collect(Collectors.toList());
    }

    public E getByID(int id, Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        String identificationFieldName = getIdentificationFieldName(fields);
        String dbPath = clazz.getAnnotation(CsvDb.class).tableName() + ".csv";
        if (!isDbFileExist(dbPath)) {
            createDbField(dbPath, fields, 1);
        }
        String[] head = getHead(dbPath);
        int identificationFieldPosition = getIdentificationPosition(head, identificationFieldName);
        String[] data = new String[0];

        if (existId(id, dbPath, identificationFieldName)) {

            data = this.getCsvDb(dbPath).stream().skip(3).filter(s -> s[identificationFieldPosition].equals("" + id)).collect(Collectors.toList()).get(0);
        }

        return getObjectFromStringArray(clazz, data);
    }

    public void delete(E e) {

        String dbPath = e.getClass().getAnnotation(CsvDb.class).tableName() + ".csv";
        String identificationFieldName = this.getIdentificationFieldName(e.getClass().getDeclaredFields());
        if (!isDbFileExist(dbPath)) {
            createDbField(dbPath, e.getClass().getDeclaredFields(), 1);
        }
        String[] head = this.getHead(dbPath);
        String[] data = getStringArrayFromObject(e);
        int identificationFieldPosition = getIdentificationPosition(head, identificationFieldName);

        if (this.existId(Integer.parseInt(data[identificationFieldPosition]), dbPath, identificationFieldName)) {
            List<String[]> db = this.getCsvDb(dbPath);
            db = db.stream().skip(3).filter(s -> !s[identificationFieldPosition].equals(data[identificationFieldPosition])).collect(Collectors.toList());
            saveDB(db, e.getClass().getDeclaredFields(), dbPath);
        }
    }

    public void update(E e) {
        String dbPath = e.getClass().getAnnotation(CsvDb.class).tableName() + ".csv";
        String identificationFieldName = this.getIdentificationFieldName(e.getClass().getDeclaredFields());
        String[] head = this.getHead(dbPath);
        String[] data = getStringArrayFromObject(e);
        int identificationFieldPosition = getIdentificationPosition(head, identificationFieldName);

        if (this.existId(Integer.parseInt(data[identificationFieldPosition]), dbPath, identificationFieldName)) {
            List<String[]> db = this.getCsvDb(dbPath);
            db = db.stream().skip(3).map(s -> {
                if (s[identificationFieldPosition].equals(data[identificationFieldPosition])) {
                    s = data;
                }
                return s;
            }).collect(Collectors.toList());
            saveDB(db, e.getClass().getDeclaredFields(), dbPath);
        }
    }

    private String getStringData(String[] data) {
        int startIndex = 0;
        int endIndex = 0;
        String datastring = "";
        for (String s : data) {
            datastring += (s + ",");
        }
        endIndex = datastring.length() - 1;
        return datastring.substring(startIndex, endIndex);
    }

    private String getIdentificationFieldName(Field[] fields) {
        for (Field f : fields) {
            if (f.isAnnotationPresent(CsvDbAutoIncrement.class)) {
                return f.getName();
            }
        }
        return "";
    }

    private List<String[]> getCsvDb(String dbPath) {
        List<String[]> stringsList = new ArrayList<>();
        String line;
        try (FileReader fileReader = new FileReader(dbPath); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while ((line = bufferedReader.readLine()) != null) {
                stringsList.add(this.fromStringToStringArr(line));
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringsList;
    }

    private String[] fromStringToStringArr(String string) {
        String[] strings = string.split(",");
        return strings;
    }

    private boolean existId(int id, String dbPath, String identificationFieldName) {
        int identificationPosition = getIdentificationPosition(getHead(dbPath), identificationFieldName);
        if (this.getCsvDb(dbPath).stream().skip(3).filter(s -> s[identificationPosition].equals("" + id)).collect(Collectors.toList()).size() == 0) {
            return false;
        }
        return true;
    }

    private String getAutoIncrement(String dbPath) {
        String increment = "";
        try (FileReader fileReader = new FileReader(dbPath); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            for (int i = 0; i < 1; i++) {
                bufferedReader.readLine();
            }
            increment = bufferedReader.readLine();

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return increment;
    }

    public void add(E e) {
        String dbPath = e.getClass().getAnnotation(CsvDb.class).tableName() + ".csv";

        if (!isDbFileExist(dbPath)) {
            createDbField(dbPath, e.getClass().getDeclaredFields(), 1);
        }

        try (FileWriter fileWriter = new FileWriter(dbPath, true); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.newLine();
            bufferedWriter.write(getStringData(getStringArrayFromObject(e)));
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String[] getStringArrayFromObject(E e) {
        Class<?> clazz = e.getClass();
        String dbPath = clazz.getAnnotation(CsvDb.class).tableName() + ".csv";
        Field[] fields = clazz.getDeclaredFields();

        String[] head;
        String[] data;
        boolean isIdentificationFieldAdd = false;

        if (!isDbFileExist(dbPath)) {
            createDbField(dbPath, fields, 1);
        }

        head = getHead(dbPath);
        data = new String[head.length];

        for (int i = 0; i < fields.length; i++) {
            String value = "";
            fields[i].setAccessible(true);
            try {
                isIdentificationFieldAdd = (!isIdentificationFieldAdd && fields[i].isAnnotationPresent(CsvDbAutoIncrement.class) && ("" + fields[i].get(e)).equals("0")) ? true : isIdentificationFieldAdd;
                value = (isIdentificationFieldAdd && fields[i].isAnnotationPresent(CsvDbAutoIncrement.class)) ? getAutoIncrement(dbPath) : ("" + fields[i].get(e));
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }

            for (int j = 0; j < head.length; j++) {
                if (fields[i].getName().equals(head[j])) {
                    data[j] = value;
                }
            }
        }
        if (isIdentificationFieldAdd) {
            updateAutoIncrement(dbPath);
        }
        return data;
    }

    private void createDbField(String dbPath, Field[] fields, int incrementValue) {
        String[] fieldsName = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fieldsName[i] = fields[i].getName();
        }
        createDbCsvFile(dbPath, fieldsName, incrementValue);
    }


    private void updateAutoIncrement(String dbPath) {
        int autoIncrement = Integer.parseInt(getAutoIncrement(dbPath)) + 1;
        List<String[]> db = getCsvDb(dbPath);
        if (db != null) {
            db.set(1, fromStringToStringArr("" + autoIncrement));
        }
        try (FileWriter fileWriter = new FileWriter(dbPath); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (int i = 0; i < db.size(); i++) {
                bufferedWriter.append(getStringData(db.get(i)));
                if (i < (db.size() - 1)) {
                    bufferedWriter.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isDbFileExist(String dbPath) {

        return new File(dbPath).exists();
    }

    private void createDbCsvFile(String dbPath, String[] fields, int incrementValue) {
        String head = getStringData(fields);
        String increment = "increment";
        try (FileWriter fileWriter = new FileWriter(dbPath); BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(increment);
            bufferedWriter.newLine();
            bufferedWriter.write("" + incrementValue);
            bufferedWriter.newLine();
            bufferedWriter.write(head);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] getHead(String dbPath) {
        String[] header = new String[0];
        try (FileReader fileReader = new FileReader(dbPath); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            for (int i = 0; i < 2; i++) {
                bufferedReader.readLine();
            }
            header = fromStringToStringArr(bufferedReader.readLine());
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return header;
    }

    private int getIdentificationPosition(String[] head, String identificationFieldName) {
        for (int i = 0; i < head.length; i++) {
            if (head[i].equals(identificationFieldName)) {
                return i;
            }
        }
        return 0;
    }

    private E getObjectFromStringArray(Class<?> clazz, String[] data) {
        String dbPath = clazz.getAnnotation(CsvDb.class).tableName() + ".csv";
        String[] head = getHead(dbPath);
        Field[] fields = clazz.getDeclaredFields();
        E e = null;
        try {
            e = (E) clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException instantiationException) {
            instantiationException.printStackTrace();
        } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
        } catch (InvocationTargetException invocationTargetException) {
            invocationTargetException.printStackTrace();
        } catch (NoSuchMethodException noSuchMethodException) {
            noSuchMethodException.printStackTrace();
        }
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getType() == int.class||fields[i].getType() == Integer.class) {
                for (int j = 0; j < head.length; j++) {
                    if (fields[i].getAnnotation(CsvMapping.class).value().equals(head[j])) {
                        try {
                            fields[i].setAccessible(true);
                            fields[i].setInt(e, Integer.parseInt(data[i]));
                        } catch (IllegalAccessException illegalAccessException) {
                            illegalAccessException.printStackTrace();
                        }
                    }
                }
            }
            if (fields[i].getType()==String.class) {
                for (int j = 0; j < head.length; j++) {
                    if (fields[i].getAnnotation(CsvMapping.class).value().equals(head[j])) {
                        try {
                            fields[i].setAccessible(true);
                            fields[i].set(e, data[i]);
                        } catch (IllegalAccessException illegalAccessException) {
                            illegalAccessException.printStackTrace();
                        }
                    }
                }
            }
            if (fields[i].getType()==long.class||fields[i].getType()==Long.class) {
                for (int j = 0; j < head.length; j++) {
                    if (fields[i].getAnnotation(CsvMapping.class).value().equals(head[j])) {
                        try {
                            fields[i].setAccessible(true);
                            fields[i].setLong(e, Long.parseLong(data[i]));
                        } catch (IllegalAccessException illegalAccessException) {
                            illegalAccessException.printStackTrace();
                        }
                    }
                }
            }
            if (fields[i].getType()==double.class||fields[i].getType()==Double.class) {
                for (int j = 0; j < head.length; j++) {
                    if (fields[i].getAnnotation(CsvMapping.class).value().equals(head[j])) {
                        try {
                            fields[i].setAccessible(true);
                            fields[i].setDouble(e, Double.parseDouble(data[i]));
                        } catch (IllegalAccessException illegalAccessException) {
                            illegalAccessException.printStackTrace();
                        }
                    }
                }
            }

            if (fields[i].getType()==char.class||fields[i].getType()==Character.class) {
                for (int j = 0; j < head.length; j++) {
                    if (fields[i].getAnnotation(CsvMapping.class).value().equals(head[j])) {
                        try {
                            fields[i].setAccessible(true);
                            fields[i].setChar(e, data[i].charAt(0));
                        } catch (IllegalAccessException illegalAccessException) {
                            illegalAccessException.printStackTrace();
                        }
                    }
                }
            }
            if (fields[i].getType()==char.class||fields[i].getType()==Character.class) {
                for (int j = 0; j < head.length; j++) {
                    if (fields[i].getAnnotation(CsvMapping.class).value().equals(head[j])) {
                        try {
                            fields[i].setAccessible(true);
                            fields[i].setChar(e, data[i].charAt(0));
                        } catch (IllegalAccessException illegalAccessException) {
                            illegalAccessException.printStackTrace();
                        }
                    }
                }
            }
            if (fields[i].getType()== LocalDate.class) {
                for (int j = 0; j < head.length; j++) {
                    if (fields[i].getAnnotation(CsvMapping.class).value().equals(head[j])) {
                        try {
                            String[] local =data[i].split("-");
                            fields[i].setAccessible(true);
                            if(local.length != 3){
                                LocalDate localDate =LocalDate.of(Integer.parseInt(local[0]),Integer.parseInt(local[1]),Integer.parseInt(local[2]));
                                fields[i].set(e, localDate );
                            } else {
                                fields[i].set(e, null );
                            }

                        } catch (IllegalAccessException illegalAccessException) {
                            illegalAccessException.printStackTrace();
                        }
                    }
                }
            }
        }
        return e;
    }

}
