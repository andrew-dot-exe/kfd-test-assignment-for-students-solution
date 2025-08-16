package com.andrewexe.entities;

public class UserGuest extends User{
    public UserGuest(String fullname, String phoneNumber, int id) {
        super(fullname, phoneNumber, id);
    }

    @Override
    public int getBookLimit() {
        return 1;
    }

    @Override
    public int getBorrowDayLimit() {
        return 7;
    }
}
