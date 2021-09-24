package com.yevheniimakar.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectorDB {
    private static Properties props = loadProperties();
    private static String url = props.getProperty("url");
    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(url, props);
    }

    private static Properties loadProperties() {

        Properties props = new Properties();

        try(InputStream input = ConnectorDB.class.getResourceAsStream("/jdbc.properties")) {
            props.load(input);
        } catch (IOException e) {
            System.err.println(e);
            throw new UncheckedIOException(e);
        }

        return props;
    }
}
