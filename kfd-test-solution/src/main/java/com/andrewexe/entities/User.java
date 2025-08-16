package com.andrewexe.entities;

public abstract class User {
    protected String fullName;
    protected String phoneNumber;
    protected int userId;

    public User(String fullname, String phoneNumber, int id) {
        this.fullName = fullname;
        this.phoneNumber = phoneNumber;
        this.userId = id;
    }

    public String getFullname() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getUserId(){
        return userId;
    }

    public abstract int getBookLimit();
    public abstract int getBorrowDayLimit();

}
