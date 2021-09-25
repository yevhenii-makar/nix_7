package com.yevheniimakar.domain;

import com.yevheniimakar.annotation.CsvDb;
import com.yevheniimakar.annotation.CsvDbAutoIncrement;
import com.yevheniimakar.annotation.CsvMapping;

import java.time.LocalDate;


enum Gender {MALE, FEMALE, UNKNOWN}

@CsvDb (tableName = "students")
public class Student {


    @CsvMapping ("gender")
    Gender gender;
    @CsvMapping ("active")
    boolean active;
    @CsvMapping ("engagementScore")
    double engagementScore;
    @CsvDbAutoIncrement
    @CsvMapping ("id")
    private int id;
    @CsvMapping ("name")
    private String name;
    @CsvMapping ("birthDate")
    private LocalDate birthDate;

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
