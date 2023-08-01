package com.library.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHandler {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/books?useSSL=false";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DATABASE_URL, "root", "Valakka@bajji");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void closeResources(Connection connection, Statement statement, ResultSet resultSet) {
        // ... (your existing closeResources() method implementation)
    }
}
