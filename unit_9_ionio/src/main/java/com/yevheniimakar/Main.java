package com.yevheniimakar;

import com.yevhenii.makar.Application;
import com.yevheniimakar.controller.CourseController;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {

        new CourseController().creatDB();
        Application.run(Main.class);
    }

}