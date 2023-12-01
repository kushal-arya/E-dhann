package com.example.project;

public class cloth {
    String Nname;
    String uname;

    public cloth() {

    }

    public cloth(String nname, String uname) {
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
