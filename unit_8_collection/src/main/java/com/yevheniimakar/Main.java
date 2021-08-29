package com.yevheniimakar;

import com.yevhenii.makar.Application;
import com.yevheniimakar.controller.StartedData;


public class Main {

    public static void main(String[] args) {
        StartedData.addData();
        Application.run(Main.class);
    }

}
