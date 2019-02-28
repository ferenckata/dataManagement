package se.chalmers.dm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {

    // DB connection configuration
    private static String DRIVER_CLASS = "org.postgresql.Driver";
    private static String DB_USER = "postgres";
    //ToDo: change password back to empty string before submission
    private static String DB_PASSWORD = "bemegyek";
    private static String DB_URL = "jdbc:postgresql://localhost:5432/websitedb";

    public static Connection createPostgresConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER_CLASS);
        Connection c = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
        return c;
    }
}
