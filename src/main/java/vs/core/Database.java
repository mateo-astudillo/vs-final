package vs.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        // String driver = System.getenv("VS_DRIVER");
        // String user = System.getenv("VS_USER");
        // String pass = System.getenv("VS_PASSWORD");
        // String url = System.getenv("VS_URL");
        String driver = "com.mysql.cj.jdbc.Driver";
        String user = "root";
        String pass = "test";
        String url = "jdbc:mysql://localhost:3307/VST";

        Class.forName(driver);
        return DriverManager.getConnection(url, user, pass);
    }
}
