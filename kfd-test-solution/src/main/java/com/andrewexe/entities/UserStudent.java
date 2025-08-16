package com.andrewexe.entities;

public class UserStudent extends User{

    public UserStudent( int id, String fullname, String phoneNumber) {
        super(id, fullname, phoneNumber);
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