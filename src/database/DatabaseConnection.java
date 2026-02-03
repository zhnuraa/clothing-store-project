package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // TODO: поменяй clothing_db на имя твоей БД
    private static final String URL = "jdbc:postgresql://localhost:5432/clothing_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "YOUR_PASSWORD_HERE";

    public static Connection getConnection() {
        try {
            Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
            return c;
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
            return null;
        }
    }

    public static void closeConnection(Connection c) {
        if (c != null) {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

