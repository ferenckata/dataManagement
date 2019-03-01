package se.chalmers.dm;

import java.sql.*;

public class JDBCTestDriver {
    // DB connection configuration
    private static String DRIVER_CLASS = "org.postgresql.Driver";
    private static String DB_USER = "postgres";
    //ToDo: change password back to empty string before submission
    private static String DB_PASSWORD = "";
    private static String DB_URL = "jdbc:postgresql://localhost:5432/websitedb";
    private static int EXIT_FAILURE = 1;

    public static void main(String[] args) {
        try{
            Class.forName(DRIVER_CLASS);
            Connection c = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT 15 AS retval;");
            while(rs.next()){
                System.out.println(rs.getString("retval"));
            }


 /*         Seeder test = new Seeder(c);
            test.createUserTable();
            test.createWebPageTable();
            test.insertFakeUsers(12);
            test.insertFakeUsersWithWebPage(200);
*/
            rs.close();
            stmt.close();
            c.close();
        }catch(Exception e){
            System.out.println(EXIT_FAILURE);
        }

    }
}
