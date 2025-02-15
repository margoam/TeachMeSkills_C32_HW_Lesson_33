package com.teachmeskills.hw.lesson_33.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    public static Properties loadProperties() {
        Properties properties = new Properties();
        try(InputStream inputStream = new FileInputStream("lesson_33/src/main/resources/config.properties")) {

            properties.load(inputStream);
        } catch (IOException e) {
            System.out.println("Error loading properties file");
        }
        return properties;
    }
}
