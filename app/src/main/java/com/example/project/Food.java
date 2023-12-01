package com.example.project;

public class Food {
    String Nname;
    String uname;

    public Food() {
    }

    public Food(String nname, String uname) {
        Nname = nname;
        this.uname = uname;
    }

    public String getNname() {
        return Nname;
    }

    public void setNname(String nname) {
        Nname = nname;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
