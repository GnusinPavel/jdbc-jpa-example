package org.gnusinpavel.itlab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {
    public static final String CONNECTION_STRING = "jdbc:postgresql://localhost:5432/itlab";

    public static void clearDb() {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                statement.execute("DELETE FROM employee");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION_STRING, "pavel", "1234");
    }
}
