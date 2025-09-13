package com.andrewexe.entities;

public class UserFaculty extends User {

    public UserFaculty( int id, String fullname, String phoneNumber) {
        super( id, fullname, phoneNumber);
    }

    @Override
    public int getBookLimit() {
        return 10;
    }

    @Override
    public int getBorrowDayLimit() {
        return 30;
    }
}
