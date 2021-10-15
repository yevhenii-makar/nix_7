package com.yevheniimakar.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class DataSource {


    private static SessionFactory sessionFactory;

    private static SessionFactory setSessionFactory() {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

        return configuration.buildSessionFactory();
    }

    public static void setSessionFactoryWithPasswordAndUser(String password, String user) {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        Properties properties = new Properties();
        properties.setProperty("hikari.dataSource.user", user);
        properties.setProperty("hikari.dataSource.password", password);
        configuration.addProperties(properties);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = setSessionFactory();
        }
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        sessionFactory.close();
    }
}
