package com.teachmeskills.hw.lesson_33.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static final String URL = PropertiesLoader.loadProperties().getProperty("db.url");
    private static final String USER = PropertiesLoader.loadProperties().getProperty("db.user");
    private static final String PASSWORD = PropertiesLoader.loadProperties().getProperty("db.password");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
