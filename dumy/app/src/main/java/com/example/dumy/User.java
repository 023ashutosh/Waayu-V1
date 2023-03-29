package com.example.dumy;

public class User {

    String name;
    int yrs;
    Double weight;
    Double height;
    String gender;
    String blood;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", yrs=" + yrs +
                ", weight=" + weight +
                ", height=" + height +
                ", gender='" + gender + '\'' +
                ", blood='" + blood + '\'' +
                '}';
    }





    public User(String name, int yrs, Double weight, Double height, String gender, String blood) {
        this.name = name;
        this.yrs = yrs;
        this.weight = weight;

        this.height = height;
        this.gender = gender;
        this.blood = blood;

    }





}
