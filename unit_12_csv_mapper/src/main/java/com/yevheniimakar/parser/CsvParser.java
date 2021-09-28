package com.yevheniimakar.parser;

import com.yevheniimakar.mapper.CsvMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CsvParser<E> {
    private static final Logger log = LoggerFactory.getLogger(CsvParser.class);

    private String[] fromStringToStringArr(String string) {
        String[] strings = string.split(",");
        return strings;
    }

    public List<String[]> getCsvDb(String dbPath) {
        List<String[]> stringsList = new ArrayList<>();
        String line;
        try (FileReader fileReader = new FileReader(dbPath); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while ((line = bufferedReader.readLine()) != null) {
                stringsList.add(this.fromStringToStringArr(line));
            }

        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return stringsList;
    }

}
