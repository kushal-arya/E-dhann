package com.example.project;

public class oldUser {
    String name;
    String age;
    String address;
    String medical;
    String location;
    String gen;

    public oldUser() {
    }

    public oldUser(String name, String age, String address, String medical, String location, String gen) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.medical = medical;
        this.location = location;
        this.gen = gen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMedical() {
        return medical;
    }

    public void setMedical(String medical) {
        this.medical = medical;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }
}
