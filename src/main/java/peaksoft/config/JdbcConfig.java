package peaksoft.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConfig {
    public static Connection getConnection(){
        Connection connection;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/movie1",
                    "postgres",
                    "1234"
            );
            System.out.println("Data base is connected!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }return connection;
    }
}
