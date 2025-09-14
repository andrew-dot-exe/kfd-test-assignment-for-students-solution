package com.andrewexe.entities;

public abstract class User extends BaseEntity{
    protected String fullName;
    protected String phoneNumber;

    public User(int id, String fullname, String phoneNumber) {
        super(id);
        this.fullName = fullname;
        this.phoneNumber = phoneNumber;
    }

    public String getFullname() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public abstract int getBookLimit();
    public abstract int getBorrowDayLimit();

}
