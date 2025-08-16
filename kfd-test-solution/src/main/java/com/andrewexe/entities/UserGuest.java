package com.andrewexe.entities;

public class UserGuest extends User{
    public UserGuest(int id, String fullname, String phoneNumber) {
        super(id, fullname, phoneNumber);
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
