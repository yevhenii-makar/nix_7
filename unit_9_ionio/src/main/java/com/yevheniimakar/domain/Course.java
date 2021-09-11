package com.yevheniimakar.domain;

import com.yevheniimakar.annotation.CsvDb;
import com.yevheniimakar.annotation.CsvDbAutoIncrement;


@CsvDb(tableName = "courses")
public class Course  {
    @CsvDbAutoIncrement
    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
