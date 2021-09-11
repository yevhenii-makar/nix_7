package com.yevheniimakar;

import com.yevhenii.makar.Application;
import com.yevheniimakar.annotation.CsvDbAutoIncrement;
import com.yevheniimakar.controller.CourseController;
import com.yevheniimakar.domain.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) throws IOException {

        new CourseController().creatDB();
        Application.run(Main.class);
    }

}