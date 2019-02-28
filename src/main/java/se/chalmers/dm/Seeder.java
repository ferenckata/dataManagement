package se.chalmers.dm;

import com.github.javafaker.Faker;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class Seeder {

    private Connection c;
    private Statement stmt;
    private ResultSet rs;
    private ArrayList<String> users;
    private Faker faker;
    private Random random;

    public Seeder(Faker faker,Connection c,Random random) throws SQLException {
        this.c = c;
        stmt = c.createStatement();
        users = new ArrayList<>();
        this.faker = faker;
        this.random = random;
    }

    public int createUserTable() throws SQLException {

       String query = QueryHelper.sqlQuery("create_user_table.sql");
       int update = stmt.executeUpdate(query);
       return update;
    }

    public void insertFakeUsers(int number) throws SQLException {

        for (int i = 0; i < number; i++) {
            String fname = faker.name().firstName();
            String lname = faker.name().lastName();
            String ssn = faker.idNumber().ssnValid();
            String email = faker.internet().emailAddress();
            boolean isActive = faker.bool().bool();
            String query = QueryHelper.sqlQuery("insert_user.sql");
            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setString(1,ssn);
            pstmt.setString(2,fname);
            pstmt.setString(3,lname);
            pstmt.setString(4,email);
            pstmt.setBoolean(5,isActive);
            pstmt.executeUpdate();

            users.add(ssn);
        }


    }


    public int createWebPageTable() throws SQLException {
        String query = QueryHelper.sqlQuery("create_webpage_table.sql");
        int update = stmt.executeUpdate(query);
        return update;
    }


    public void insertFakeUsersWithWebPage(int number) throws SQLException {
        int start= users.size();
        insertFakeUsers(number);
        for(int i= start; i< number+start; i++){
            String content = faker.chuckNorris().fact();
            double popularity= Math.random()*100;
            String Url= faker.internet().url();
            String query = QueryHelper.sqlQuery("insert_webpage.sql");
            PreparedStatement pstmt = c.prepareStatement(query );
            pstmt.setString(1,Url);
            pstmt.setInt(2,i);
            pstmt.setString(3,content);
            pstmt.setInt(4,(int)(popularity));
            pstmt.executeUpdate();


        }

    }



}
