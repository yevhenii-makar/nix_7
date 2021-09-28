package com.yevheniimakar;

import com.yevheniimakar.appproperty.AppProperties;
import com.yevheniimakar.factory.Factory;
import com.yevheniimakar.service.impl.PropertyServiceImpl;

import java.util.Properties;


public class Main {

    public static void main(String[] args) {
        Properties properties = new PropertyServiceImpl().getProperties();
        Factory factory = new Factory();

        AppProperties appProperties = (AppProperties) factory.map(AppProperties.class, properties);
    }

}
