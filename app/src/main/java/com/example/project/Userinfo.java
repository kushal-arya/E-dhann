package com.example.project;

public class Userinfo {
    public String userName;
    public String userAge;
    public String userEmail;
    public String userPhonenumber;
    String bloodgp ;
    String gender;
    String bodysize;
    String prevdes;
    String odonatetype;
    String brain;
    String eye;
    String heart;
    String kidney;
    String lungs;
    String liver;
    String smallint;
    String pancs;
    String largeintestine;
    String code;
    String amount;

    public Userinfo(String amount) {
        this.amount = amount;
    }

    public Userinfo(String code, String userName, String userAge, String userEmail, String userPhonenumber, String bloodgp, String gender, String bodysize, String prevdes, String odonatetype, String brain, String eye, String heart, String kidney, String lungs, String liver, String smallint, String pancs, String largeintestine) {
        this.userName = userName;
        this.userAge = userAge;
        this.code = code;
        this.userEmail = userEmail;
        this.userPhonenumber = userPhonenumber;
        this.bloodgp = bloodgp;
        this.gender = gender;
        this.bodysize = bodysize;
        this.prevdes = prevdes;
        this.odonatetype = odonatetype;
        this.brain = brain;
        this.eye = eye;
        this.heart = heart;
        this.kidney = kidney;
        this.lungs = lungs;
        this.liver = liver;
        this.smallint = smallint;
        this.pancs = pancs;
        this.largeintestine = largeintestine;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBrain() {
        return brain;
    }

    public void setBrain(String brain) {
        this.brain = brain;
    }

    public String getEye() {
        return eye;
    }

    public void setEye(String eye) {
        this.eye = eye;
    }

    public String getHeart() {
        return heart;
    }

    public void setHeart(String heart) {
        this.heart = heart;
    }

    public String getKidney() {
        return kidney;
    }

    public void setKidney(String kidney) {
        this.kidney = kidney;
    }

    public String getLungs() {
        return lungs;
    }

    public void setLungs(String lungs) {
        this.lungs = lungs;
    }

    public String getLiver() {
        return liver;
    }

    public void setLiver(String liver) {
        this.liver = liver;
    }

    public String getSmallint() {
        return smallint;
    }

    public void setSmallint(String smallint) {
        this.smallint = smallint;
    }

    public String getPancs() {
        return pancs;
    }

    public void setPancs(String pancs) {
        this.pancs = pancs;
    }

    public String getLargeintestine() {
        return largeintestine;
    }

    public void setLargeintestine(String largeintestine) {
        this.largeintestine = largeintestine;
    }

    public String getOdonatetype() {
        return odonatetype;
    }

    public void setOdonatetype(String odonatetype) {
        this.odonatetype = odonatetype;
    }

    public String getBodysize() {
        return bodysize;
    }

    public void setBodysize(String bodysize) {
        this.bodysize = bodysize;
    }

    public String getPrevdes() {
        return prevdes;
    }

    public void setPrevdes(String prevdes) {
        this.prevdes = prevdes;
    }

    @Override
    public String toString() {
        return "Userinfo{" +
                "userName='" + userName + '\'' +
                ", userAge='" + userAge + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhonenumber='" + userPhonenumber + '\'' +
                ", bloodgp='" + bloodgp + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public Userinfo() {

    }

    public String getBloodgp() {
        return bloodgp;
    }

    public void setBloodgp(String bloodgp) {
        this.bloodgp = bloodgp;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserPhonenumber() {
        return userPhonenumber;
    }

    public void setUserPhonenumber(String userPhonenumber) {
        this.userPhonenumber = userPhonenumber;
    }



    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}


