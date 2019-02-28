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
       int update = stmt.executeUpdate("CREATE TABLE tbl_user(ID serial primary key,Ssn char(11),FName varchar,LName varchar ,email varchar ,isActive boolean);");
       return update;
    }

    public void insertFakeUsers(int number) throws SQLException {

        for (int i = 0; i < number; i++) {
            String fname = faker.name().firstName();
            String lname = faker.name().lastName();
            String ssn = faker.idNumber().ssnValid();
            String email = faker.internet().emailAddress();
            boolean isActive = faker.bool().bool();
            PreparedStatement pstmt = c.prepareStatement("insert into tbl_user(Ssn,FName,LName,email,isActive) values(?,?,?,?,?);");
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
        int update = stmt.executeUpdate("CREATE TABLE Webpage(id serial primary key, Url varchar, Author integer, content varchar, popularity integer, foreign key (Author) references tbl_user(id), check (popularity>=0 and popularity<= 100) );");
        return update;
    }


    public void insertFakeUsersWithWebPage(int number) throws SQLException {
        int start= users.size();
        insertFakeUsers(number);
        for(int i= start; i< number+start; i++){
            String content = faker.chuckNorris().fact();
            double popularity= Math.random()*100;
            String Url= faker.internet().url();
            PreparedStatement pstmt = c.prepareStatement("insert into WebPage(Url, Author, content, popularity )values(?,?,?,?);" );
            pstmt.setString(1,Url);
            pstmt.setInt(2,i);
            pstmt.setString(3,content);
            pstmt.setInt(4,(int)(popularity));
            pstmt.executeUpdate();


        }

    }



}
