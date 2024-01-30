package com.datasite.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = loadProperties();

    private ConfigReader() {
    }

    private static Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream input = DriverFactory.class.getClassLoader().getResourceAsStream("config.properties")) {
            props.load(input);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return props;
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

}
