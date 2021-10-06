package com.yevheniimakar.service.impl;

import com.yevheniimakar.service.PropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;


public class PropertyServiceImpl implements PropertyService {

    private static final Logger log = LoggerFactory.getLogger(PropertyServiceImpl.class);

    public Properties getProperties() {

        Properties props = new Properties();

        try (InputStream input = PropertyServiceImpl.class.getResourceAsStream("/app.properties")) {
            props.load(input);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new UncheckedIOException(e);
        }
        return props;
    }

}
