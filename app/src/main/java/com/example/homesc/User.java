package com.example.homesc;

public class User {
    public String fullName, userEmail, userPhone;
    public int numberOfAvailablePoints;
    public User(){}
    public User(String fullName, String userEmail, String userPhone,int numberOfAvailablePoints)
    {
        this.fullName = fullName;
        this.userEmail= userEmail;
        this.userPhone= userPhone;
        this.numberOfAvailablePoints = numberOfAvailablePoints;

    }
}

