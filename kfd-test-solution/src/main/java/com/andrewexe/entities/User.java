package com.andrewexe.entities;

public abstract class User {
    protected String fullName;
    protected String phoneNumber;
    protected int userId;
    protected UserType userType;

    public User(int id, String fullname, String phoneNumber) {
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

    @Override
    public String toString(){
        return userId + ": " + fullName + " " +phoneNumber ;
    }
}
