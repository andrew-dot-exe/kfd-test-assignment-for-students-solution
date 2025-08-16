package com.andrewexe.entities;

public class UserStudent extends User{

    public UserStudent(String fullname, String phoneNumber, int id) {
        super(fullname, phoneNumber, id);
    }

    @Override
    public int getBookLimit() {
        return 3;
    }

    @Override
    public int getBorrowDayLimit() {
        return 10;
    }
}