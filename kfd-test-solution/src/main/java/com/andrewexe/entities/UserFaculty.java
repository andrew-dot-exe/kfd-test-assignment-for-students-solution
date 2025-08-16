package com.andrewexe.entities;

public class UserFaculty extends User {

    public UserFaculty(String fullname, String phoneNumber, int id) {
        super(fullname, phoneNumber, id);
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
