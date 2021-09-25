package com.yevheniimakar.service.impl;

import com.yevheniimakar.service.PropertyService;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;


public class PropertyServiceImpl implements PropertyService {

    public Properties getProperties() {

        Properties props = new Properties();

        try(InputStream input = PropertyServiceImpl.class.getResourceAsStream("/app.properties")) {
            props.load(input);
        } catch (IOException e) {
            System.err.println(e);
            throw new UncheckedIOException(e);
        }

        return props;


    }

}
