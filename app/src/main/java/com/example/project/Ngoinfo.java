package com.example.project;

public class Ngoinfo {
    public String userName;
    public String userAddres;
    public String userEmail;
    public String userPhonenumber;
    public String ngotype;
    public String ncode;
    public String food;
    public String cloth;
    public String money;
    public String comanyinfo;

    public Ngoinfo() {

    }

    public Ngoinfo(String userName, String userAddres, String userEmail, String userPhonenumber, String ngotype, String ncode, String food, String cloth, String money, String comanyinfo) {
        this.userName = userName;
        this.userAddres = userAddres;
        this.userEmail = userEmail;
        this.userPhonenumber = userPhonenumber;
        this.ngotype = ngotype;
        this.ncode = ncode;
        this.food = food;
        this.cloth = cloth;
        this.money = money;
        this.comanyinfo = comanyinfo;
    }

    public String getComanyinfo() {
        return comanyinfo;
    }

    public void setComanyinfo(String comanyinfo) {
        this.comanyinfo = comanyinfo;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getCloth() {
        return cloth;
    }

    public void setCloth(String cloth) {
        this.cloth = cloth;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getNcode() {
        return ncode;
    }

    public void setNcode(String ncode) {
        this.ncode = ncode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddres() {
        return userAddres;
    }

    public void setUserAddres(String userAddres) {
        this.userAddres = userAddres;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhonenumber() {
        return userPhonenumber;
    }

    public void setUserPhonenumber(String userPhonenumber) {
        this.userPhonenumber = userPhonenumber;
    }

    public String getNgotype() {
        return ngotype;
    }

    public void setNgotype(String ngotype) {
        this.ngotype = ngotype;
    }
}
