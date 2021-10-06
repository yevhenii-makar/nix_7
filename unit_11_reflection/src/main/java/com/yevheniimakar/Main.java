package com.yevheniimakar;

import com.yevheniimakar.appproperty.AppProperties;
import com.yevheniimakar.factory.Factory;
import com.yevheniimakar.service.impl.PropertyServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Properties;


public class Main {
    private static final Logger log = LoggerFactory.getLogger(Factory.class);
    public static void main(String[] args) {

        Properties properties = new PropertyServiceImpl().getProperties();
        Factory factory = new Factory();

        AppProperties appProperties = null;
        try {
            appProperties = (AppProperties) factory.map(AppProperties.class, properties);
        } catch (IllegalAccessException e) {
            log.error(e.getMessage());
        } catch (NoSuchMethodException e) {
            log.error(e.getMessage());
        } catch (InvocationTargetException e) {
            log.error(e.getMessage());
        } catch (InstantiationException e) {
            log.error(e.getMessage());
        }

        System.out.println(appProperties);
    }

}
