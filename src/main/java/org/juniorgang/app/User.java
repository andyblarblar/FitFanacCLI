package org.juniorgang.app;

public class User extends org.juniorgang.util.User {
    private String fname;
    private String lname;

    public User(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
    }

    public User() {
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    @Override
    public String toString() {
        return "org.juniorgang.app.User{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                '}';
    }
}
