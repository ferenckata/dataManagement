package se.chalmers.dm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Queries {

    public Queries(){

    }

    public List<User> findInactiveUsers(Connection connection) throws SQLException {

        List<User> users = new ArrayList<>();

        Statement stmt = connection.createStatement();
        String query = QueryHelper.sqlQuery("find_inactive_users.sql");
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()){
            int ID = rs.getInt("id");
            String ssn = rs.getString("ssn");
            String fname= rs.getString("fname");
            String lname= rs.getString("lname");
            String email= rs.getString("email");
            Boolean isActive= rs.getBoolean("isActive");
            User newUser = new User(ID,ssn,fname,lname,email,isActive);
            users.add(newUser);
        }
        rs.close();
        stmt.close();
        return users;
    }
    public List<String> findSpecialQuotes(Connection connection) throws SQLException {
        List<String> quotes= new ArrayList<>();

        Statement stmt = connection.createStatement();
        String query = QueryHelper.sqlQuery("find_special_quotes.sql");
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()) {
            String quote= rs.getString("content");
            quotes.add(quote);
        }
        rs.close();
        stmt.close();
        connection.close();
        return quotes;



    }

}
