package com.yevheniimakar;


import com.yevheniimakar.annotation.CsvMapping;
import com.yevheniimakar.domain.Student;
import com.yevheniimakar.mapper.CsvMapper;
import com.yevheniimakar.parser.CsvParser;

import java.util.List;


public class Main {

    public static void main(String[] args)  {

        CsvParser csvParser = new CsvParser();
        CsvMapper csvMapper =  new CsvMapper();

        List<Student> studentList = csvMapper.getObjectList(Student.class,csvParser.getCsvDb(args[0]));

        studentList.stream().forEach(System.out::println);

    }

}