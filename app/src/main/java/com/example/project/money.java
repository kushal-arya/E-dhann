package com.example.project;

public class money {
    String Nname;
    String uname;
    String ammount;

    public money() {
    }

    public money(String nname, String uname, String ammount) {
        Nname = nname;
        this.uname = uname;
        this.ammount = ammount;
    }

    public String getAmmount() {
        return ammount;
    }

    public void setAmmount(String ammount) {
        this.ammount = ammount;
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