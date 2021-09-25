package com.yevheniimakar;

import com.yevheniimakar.service.impl.PropertyServiceImpl;

import java.util.Properties;


public class Main {

    public static void main(String[] args) {
        Properties properties = new PropertyServiceImpl().getProperties();

        for (Object o: properties.entrySet()) {
            System.out.println(o);
        }


    }
}
