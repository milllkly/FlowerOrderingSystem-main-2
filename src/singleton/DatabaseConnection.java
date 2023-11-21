package singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance = new DatabaseConnection();
    private final Connection connection;

    private DatabaseConnection() throws RuntimeException {
        try {
            Class.forName("org.postgresql.Driver");
            // Update these details according to your PostgreSQL setup
            String url = "jdbc:postgresql://localhost:5432/mytable";
            String user = "postgres";
            String password = "87470147791";
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
