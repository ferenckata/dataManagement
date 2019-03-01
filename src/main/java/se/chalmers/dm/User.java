package se.chalmers.dm;

public class User {
    private int ID;
    private String ssn;
    private String fname;
    private String lname;
    private String email;
    private Boolean isActive;


    public User(int id,String ssn, String fname, String lname, String email, boolean isActive ){
        this.ID = id;
        this.ssn = ssn;
        this.fname= fname;
        this.lname= lname;
        this.email= email;
        this.isActive= isActive;
    }

    @Override
    public String toString() {
        String userInfo = this.ID + ";" + this.ssn + ";" + this.fname + ";" + this.lname + ";" + this.email + ";" + this.isActive;
        return userInfo;
    }
}
